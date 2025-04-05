package com.example.cusCom.component

import com.example.cusCom.config.JwtConfig
import com.example.cusCom.dto.JwtDTO
import com.example.cusCom.enums.JwtWhitelistPath
import com.example.cusCom.exception.CusComErrorCode
import com.example.cusCom.exception.CusComException
import com.example.cusCom.service.CustomUserDetailsService
import com.example.cusCom.service.TokenService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.AntPathMatcher
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*
import java.util.stream.Collectors
import javax.crypto.SecretKey

@Component
class JwtComponent(private val jwtProperties: JwtConfig,
                   private val customUserDetailsService: CustomUserDetailsService,
                   private val tokenService: TokenService) : OncePerRequestFilter() {

    private lateinit var key: SecretKey

    @PostConstruct
    fun init() {
        val keyBytes = Base64.getDecoder().decode(jwtProperties.secret)
        key = Keys.hmacShaKeyFor(keyBytes)
    }

    override fun doFilterInternal(request: HttpServletRequest,
                                  response: HttpServletResponse,
                                  filterChain: FilterChain) {
        val requestURI = request.requestURI

        if (JwtWhitelistPath.allPatterns().any { AntPathMatcher().match(it, requestURI) }) {
            filterChain.doFilter(request, response)
            return
        }

        val bearerToken:String = request.getHeader("Authorization")
            ?: throw CusComException(CusComErrorCode.UnauthorizedToken)
        val token = bearerToken.substring(7)

        val isValidate = validateToken(token)
        val isBlacklisted = tokenService.isTokenBlacklisted(token)
        if (!isValidate || isBlacklisted) {
            throw CusComException(CusComErrorCode.UnauthorizedToken)
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

        return JwtDTO(accessToken,refreshToken)
    }

    @Transactional
    fun reissueAccessToken(deprecatedAccessToken:String, refreshToken:String, userDetail:UserDetails): JwtDTO {
        val isValidate = validateToken(refreshToken)
        val isBlacklisted = tokenService.isTokenBlacklisted(refreshToken)
        if (!isValidate || isBlacklisted) {
            throw CusComException(CusComErrorCode.UnauthorizedToken)
        }

        val authentication = UsernamePasswordAuthenticationToken(userDetail.username, userDetail.password)

        tokenService.saveBlacklistToken(deprecatedAccessToken)
        tokenService.saveBlacklistToken(refreshToken)
        tokenService.removeRefreshToken(refreshToken)

        val newAccessToken = generateToken(authentication)
        tokenService.saveRefreshToken(newAccessToken.refreshToken, userDetail.username)

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