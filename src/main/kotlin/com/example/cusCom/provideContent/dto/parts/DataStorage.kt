package com.example.cusCom.provideContent.dto.parts

data class DataStorage(val id:Long?=null,
                       val name:String="",
                       val imageUrl:String="",
                       val manufacturer:String="",
                       val storageInterface:String="",
                       val formFactor:String="",
                       val capacity:String="",
                       val readSpeed:Int=0,
                       val writeSpeed:Int=0){
}
