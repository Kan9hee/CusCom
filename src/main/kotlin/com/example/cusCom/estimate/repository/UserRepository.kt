package com.example.cusCom.estimate.repository

import com.example.cusCom.estimate.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<UserEntity,Long> {
    fun findByUserName(userName: String): UserEntity?
}