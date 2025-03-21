package com.example.cusCom.dto

data class JwtDTO(val grantType:String,
                  val accessToken:String,
                  val refreshToken:String)