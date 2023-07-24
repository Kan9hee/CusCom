package com.example.cusCom.provideContent.service

import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class CustomUserDetailsService(private val userService: UserService): UserDetailsService {
    override fun loadUserByUsername(userName: String): UserDetails {
        val foundUser = userService.findUser(userName)?:throw ChangeSetPersister.NotFoundException()
        return org.springframework.security.core.userdetails.User.builder()
            .username(foundUser.userName)
            .password(foundUser.userPassword)
            .roles(foundUser.userRole)
            .build()
    }
}