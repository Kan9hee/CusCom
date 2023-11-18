package com.example.cusCom.provideContent.dto.parts

data class Memory(val id:Long?=null,
                  val name:String="",
                  val imageUrl:String="",
                  val manufacturer:String="",
                  val type:String="",
                  val capacity:Int=0,
                  val height:Int=0){
}
