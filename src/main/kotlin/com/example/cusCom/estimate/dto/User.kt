package com.example.cusCom.estimate.dto

import com.example.cusCom.estimate.entity.UserEntity
import org.springframework.security.crypto.password.PasswordEncoder

data class User(val userName: String,
                val userPassword: String,
                val userRole: String) {

    fun toEntity(): UserEntity {
        return UserEntity(userName, userPassword, userRole)
    }
}