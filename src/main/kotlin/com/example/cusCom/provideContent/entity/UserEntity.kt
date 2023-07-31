package com.example.cusCom.provideContent.entity

import com.example.cusCom.provideContent.dto.User
import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import org.springframework.security.crypto.password.PasswordEncoder

@Table(name="user")
@Entity
class UserEntity(user: User) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long?=null

    @Column(nullable = false, unique = true)
    val userName:String=user.userName

    @Column(nullable = false)
    var userPassword:String=user.userPassword
        protected set

    @Column(nullable = false)
    val userRole:String=user.userRole
}