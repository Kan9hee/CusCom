package com.example.cusCom.component

import com.example.cusCom.dto.JwtDTO
import com.example.cusCom.service.CustomUserDetailsService
import com.example.cusCom.service.TokenService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*
import java.util.Base64.Decoder
import java.util.stream.Collectors
import javax.crypto.SecretKey

@Component
class JwtComponent(@Value("\${jwt.secret}") secretKey:String,
                   private val decoder:Decoder,
                   private val customUserDetailsService: CustomUserDetailsService,
                   private val tokenService: TokenService) : OncePerRequestFilter() {

    private val key: SecretKey
    init {
        val keyBytes = decoder.decode(secretKey)
        key = Keys.hmacShaKeyFor(keyBytes)
    }

    override fun doFilterInternal(request: HttpServletRequest,
                                  response: HttpServletResponse,
                                  filterChain: FilterChain) {
        val bearerToken:String = request.getHeader("Authorization")
            ?: throw IllegalArgumentException("권한 없는 토큰입니다.")
        val token = bearerToken.substring(7)

        val isValidate = validateToken(token)
        val isBlacklisted = tokenService.isTokenBlacklisted(token)
        if (!isValidate || isBlacklisted) {
            throw IllegalArgumentException("권한 없는 토큰입니다.")
        }

        val authentication = getAuthentication(token)
        SecurityContextHolder.getContext().authentication = authentication
        filterChain.doFilter(request, response)
    }

    fun generateToken(authentication: Authentication): JwtDTO {
        val authorities = authentication
            .authorities
            .stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","))

        val now = Date()

        val accessToken = Jwts.builder()
            .subject(authentication.name)
            .issuedAt(now)
            .claim("auth",authorities)
            .expiration(Date(now.time+600_000))
            .signWith(this.key)
            .compact()

        val refreshToken = Jwts.builder()
            .expiration(Date(now.time + 604_800_000))
            .signWith(this.key)
            .compact()

        return JwtDTO("Bearer ",accessToken,refreshToken)
    }

    @Transactional
    fun reissueAccessToken(refreshToken: String): JwtDTO {
        val isValidate = validateToken(refreshToken)
        val isBlacklisted = tokenService.isTokenBlacklisted(refreshToken)
        if (!isValidate || isBlacklisted) {
            throw IllegalArgumentException("권한 없는 토큰입니다.")
        }

        val accountIdString = tokenService.getAccountIdFromRefreshToken(refreshToken)
            ?: throw RuntimeException("리프레시 토큰에 저장된 계정 정보가 없습니다.")

        val user = customUserDetailsService.loadUserByUsername(accountIdString)
        val authentication = UsernamePasswordAuthenticationToken(user.username, user.password)

        tokenService.saveBlacklistToken(refreshToken)
        tokenService.removeRefreshToken(refreshToken)

        val newAccessToken = generateToken(authentication)
        tokenService.saveRefreshToken(newAccessToken.refreshToken, user.username)

        return newAccessToken
    }

    fun validateToken(token:String): Boolean {
        val claims = Jwts.parser()
            .verifyWith(this.key)
            .build()
            .parseSignedClaims(token)
            .payload

        val expirationDate = claims.expiration
        return !expirationDate.before(Date())
    }

    fun getUsernameFrom(accessToken:String): String {
        val userName = Jwts.parser()
            .verifyWith(this.key)
            .build()
            .parseSignedClaims(accessToken)
            .payload
            .subject

        return userName
    }

    fun getAuthentication(token: String): Authentication {
        val username = getUsernameFrom(token)
        val userDetails = customUserDetailsService.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
    }
}