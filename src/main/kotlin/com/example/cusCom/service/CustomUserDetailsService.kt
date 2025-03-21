package com.example.cusCom.service

import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class CustomUserDetailsService(private val userService: UserService): UserDetailsService {
    override fun loadUserByUsername(userId: String): UserDetails {
        val foundUser = userService.findUser(userId)?:throw ChangeSetPersister.NotFoundException()
        return org.springframework.security.core.userdetails.User.builder()
            .username(foundUser.accountId)
            .password(foundUser.accountPassword)
            .roles(foundUser.accountRole.toString())
            .build()
    }
}