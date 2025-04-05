package com.example.cusCom.repository.auth

import com.example.cusCom.entity.mySQL.auth.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity,Long> {
    fun findByAccountId(accountId: String): UserEntity?
    fun deleteByAccountId(accountId:String)
}