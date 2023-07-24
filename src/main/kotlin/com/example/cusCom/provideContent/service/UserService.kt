package com.example.cusCom.provideContent.service

import com.example.cusCom.provideContent.dto.User
import com.example.cusCom.provideContent.entity.UserEntity
import com.example.cusCom.provideContent.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(private val passwordEncoder: PasswordEncoder,
                  private val userRepo: UserRepository) {
    @Transactional
    fun joinUser(user:User){
        userRepo.save(UserEntity(user,passwordEncoder))
    }

    @Transactional(readOnly = true)
    fun findUser(userName:String):User?{
        val foundUser: UserEntity = userRepo.findByUserName(userName) ?: return null
        return User(foundUser.userName,foundUser.userPassword,foundUser.userRole)
    }
}