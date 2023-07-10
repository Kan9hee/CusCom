package com.example.cusCom.estimate.service

import com.example.cusCom.estimate.dto.User
import com.example.cusCom.estimate.entity.UserEntity
import com.example.cusCom.estimate.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(private val passwordEncoder: PasswordEncoder,
                  private val userRepo: UserRepository) {
    @Transactional
    fun joinUser(information:User){
        userRepo.save(information.joinEntity(passwordEncoder))
    }

    @Transactional(readOnly = true)
    fun findUser(userName:String):User?{
        val foundUser:UserEntity? = userRepo.findByUserName(userName)
        if(foundUser == null) return null
        else return User(foundUser.userName,foundUser.userPassword,foundUser.userRole)
    }
}