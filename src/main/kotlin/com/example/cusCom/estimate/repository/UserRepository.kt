package com.example.cusCom.estimate.repository

import com.example.cusCom.estimate.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity,Long> {
}