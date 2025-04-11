package com.example.cusCom.service

import com.example.cusCom.config.SecurityConfig
import com.example.cusCom.dto.request.LogInDTO
import com.example.cusCom.dto.request.LogOutDTO
import com.example.cusCom.dto.request.SignInDTO
import com.example.cusCom.enums.AccountRole
import com.example.cusCom.entity.mySQL.auth.UserEntity
import com.example.cusCom.exception.CusComErrorCode
import com.example.cusCom.exception.CusComException
import com.example.cusCom.repository.auth.UserRepository
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(private val authenticationManagerBuilder: AuthenticationManagerBuilder,
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
                AccountRole.ROLE_USER
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
        userRepo.deleteByAccountId(accountId)
    }

    @Transactional
    fun logIn(logInDTO: LogInDTO): Authentication? {
        val userInfo = findUser(logInDTO.insertedID)
            ?: throw CusComException(CusComErrorCode.UserDataNotFound)
        val trimmedPassword = logInDTO.insertedPassword.trim()

        if(!securityConfig.passwordEncoder().matches(trimmedPassword,userInfo.accountPassword))
            throw CusComException(CusComErrorCode.PasswordNotPatch)

        val authenticationToken = UsernamePasswordAuthenticationToken(
            logInDTO.insertedID,
            trimmedPassword
        )

        return authenticationManagerBuilder.`object`.authenticate(authenticationToken)
    }

    @Transactional
    fun logOut(logOutDTO: LogOutDTO) {
        tokenService.saveBlacklistToken(logOutDTO.refreshToken)
        tokenService.removeBlacklistToken(logOutDTO.refreshToken)
    }

    @Transactional
    fun findUser(userAccountId:String): UserEntity? {
        return userRepo.findByAccountId(userAccountId)
    }
}