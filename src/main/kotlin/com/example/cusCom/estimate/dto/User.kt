package com.example.cusCom.estimate.dto

import com.example.cusCom.estimate.entity.UserEntity

data class User(val userName: String,
                val userPassword: String) {

    fun toEntity(): UserEntity {
        return UserEntity(userName,userPassword)
    }
}