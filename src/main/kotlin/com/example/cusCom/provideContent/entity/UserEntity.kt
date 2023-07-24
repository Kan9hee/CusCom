package com.example.cusCom.provideContent.entity

import com.example.cusCom.provideContent.dto.User
import jakarta.persistence.*
import org.springframework.security.crypto.password.PasswordEncoder

@Table(name="user")
@Entity
class UserEntity(user: User,passwordEncoder: PasswordEncoder) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long?=null
    @Column(nullable = false, unique = true)
    var userName:String=user.userName
    @Column(nullable = false)
    var userPassword:String=passwordEncoder.encode(user.userPassword)
    @Column(nullable = false)
    var userRole:String=user.userRole
}