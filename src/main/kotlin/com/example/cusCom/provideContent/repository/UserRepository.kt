package com.example.cusCom.provideContent.repository

import com.example.cusCom.provideContent.entity.mySQL.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity,Long> {
    fun findByUserName(userName: String): UserEntity?
}