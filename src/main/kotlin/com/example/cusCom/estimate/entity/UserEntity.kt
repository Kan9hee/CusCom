package com.example.cusCom.estimate.entity

import jakarta.persistence.*

@Table(name="user")
@Entity
class UserEntity(userName:String,
                 userPassword:String) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long?=null
    @Column(nullable = false, unique = true)
    val userName:String=userName
    @Column(nullable = false, unique = true)
    val userPassword:String=userPassword
}