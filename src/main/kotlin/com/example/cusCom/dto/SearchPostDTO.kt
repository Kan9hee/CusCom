package com.example.cusCom.dto

data class SearchPostDTO(val option:String,
                         val keyword:String?,
                         val maxContent:Int,
                         val page:Int)
