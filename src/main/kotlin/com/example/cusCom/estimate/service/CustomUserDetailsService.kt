package com.example.cusCom.estimate.service

import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder

class CustomUserDetailsService(private val passwordEncoder:PasswordEncoder,
                               private val userService: UserService): UserDetailsService {
    override fun loadUserByUsername(userName: String): UserDetails {
        val foundUser = userService.findUser(userName)?:throw ChangeSetPersister.NotFoundException()
        return org.springframework.security.core.userdetails.User.builder()
            .username(foundUser.userName)
            .password(foundUser.userPassword)
            .roles(passwordEncoder.encode(foundUser.userRole))
            .build()
    }
}