package com.example.cusCom.entity.mySQL.auth

import com.example.cusCom.entity.mySQL.AccountRole
import jakarta.persistence.*

@Table(name="user")
@Entity
class UserEntity(
    @Column(unique = true)
    val accountId:String,

    accountPassword:String,
    userName:String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val accountRole: AccountRole
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long?=null

    @Column
    var accountPassword:String=accountPassword
        protected set

    @Column(unique = true)
    var userName:String = userName
        protected set

    fun setAccountPassword(encodedPasswordString:String){
        this.accountPassword=encodedPasswordString
    }

    fun setUserName(changedUserName:String){
        this.userName=changedUserName
    }
}