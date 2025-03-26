package com.example.cusCom.service

import com.example.cusCom.component.JwtComponent
import com.example.cusCom.config.SecurityConfig
import com.example.cusCom.dto.JwtDTO
import com.example.cusCom.dto.request.LogInDTO
import com.example.cusCom.dto.response.LogOutDTO
import com.example.cusCom.dto.request.SignInDTO
import com.example.cusCom.entity.mySQL.AccountRole
import com.example.cusCom.entity.mySQL.auth.UserEntity
import com.example.cusCom.exception.CusComErrorCode
import com.example.cusCom.exception.CusComException
import com.example.cusCom.repository.auth.UserRepository
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(private val jwtComponent: JwtComponent,
                  private val authenticationManagerBuilder: AuthenticationManagerBuilder,
                  private val securityConfig: SecurityConfig,
                  private val userRepo: UserRepository,
                  private val tokenService: TokenService) {

    @Transactional
    fun joinUser(signInDTO: SignInDTO): Boolean {
        return try{
            val encodedPassword = securityConfig.passwordEncoder().encode(signInDTO.insertedPassword)
            val newUser = UserEntity(
                signInDTO.insertedID,
                encodedPassword,
                signInDTO.insertedNickname,
                AccountRole.USER
            )
            userRepo.save(newUser)
            true
        } catch (e:Exception) {
            false
        }
    }

    @Transactional
    fun signOutUser(signOutDTO: LogOutDTO){
        val accountId = tokenService.getAccountIdFromRefreshToken(signOutDTO.refreshToken)
            ?: throw CusComException(CusComErrorCode.UserDataNotFound)
        logOut(signOutDTO)
        userRepo.deleteAccountId(accountId)
    }

    @Transactional
    fun logIn(logInDTO: LogInDTO): JwtDTO {
        val userInfo = findUser(logInDTO.insertedID)
            ?: throw CusComException(CusComErrorCode.UserDataNotFound)
        val trimmedPassword = logInDTO.insertedPassword.trim()

        if(!securityConfig.passwordEncoder().matches(trimmedPassword,userInfo.accountPassword))
            throw CusComException(CusComErrorCode.PasswordNotPatch)

        val authenticationToken = UsernamePasswordAuthenticationToken(
            logInDTO.insertedID,
            trimmedPassword
        )
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)
        val generatedJwt = jwtComponent.generateToken(authentication)
        tokenService.saveRefreshToken(generatedJwt.refreshToken, logInDTO.insertedID)

        return generatedJwt
    }

    @Transactional
    fun logOut(logOutDTO: LogOutDTO) {
        logOutDTO.accessToken?.takeIf { jwtComponent.validateToken(it) }?.let {
            tokenService.saveBlacklistToken(logOutDTO.accessToken)
        }

        tokenService.saveBlacklistToken(logOutDTO.refreshToken)
        tokenService.removeBlacklistToken(logOutDTO.refreshToken)
    }

    @Transactional
    fun findUser(userAccountId:String): UserEntity? {
        return userRepo.findByAccountId(userAccountId)
    }
}