package com.example.cusCom.provideContent.dto

import com.example.cusCom.provideContent.entity.UserEntity
import org.springframework.security.crypto.password.PasswordEncoder

data class User(val userName: String,
                val userPassword: String,
                val userRole: String) {

    fun toEntity(): UserEntity {
        return UserEntity(userName, userPassword, userRole)
    }

    fun joinEntity(passwordEncoder:PasswordEncoder):UserEntity{
        return UserEntity(userName, passwordEncoder.encode(userPassword), userRole)
    }
}