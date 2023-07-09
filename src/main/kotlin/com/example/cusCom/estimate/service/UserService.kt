package com.example.cusCom.estimate.service

import com.example.cusCom.estimate.dto.User
import com.example.cusCom.estimate.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(private val userRepo: UserRepository) {

    @Transactional
    fun joinUser(information:User){
        userRepo.save(information.toEntity())
    }
}