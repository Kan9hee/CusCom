package com.example.cusCom.provideContent.entity

import jakarta.persistence.*

@Table(name="user")
@Entity
class UserEntity(userName:String,
                 userPassword:String,
                 userRole:String) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long?=null
    @Column(nullable = false, unique = true)
    val userName:String=userName
    @Column(nullable = false)
    val userPassword:String=userPassword
    @Column(nullable = false)
    val userRole:String=userRole
}