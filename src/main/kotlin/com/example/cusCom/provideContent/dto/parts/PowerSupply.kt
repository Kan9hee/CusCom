package com.example.cusCom.provideContent.dto.parts

data class PowerSupply(val id:Long?=null,
                       val name:String="",
                       val imageUrl:String="",
                       val manufacturer:String="",
                       val power:Int=0,
                       val efficiency:String="NONE",
                       val modular:String="",
                       val length:Int=0){
}
