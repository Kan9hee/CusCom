package com.example.cusCom.provideContent.dto.parts

data class CPUCooler(val id:Long?=null,
                     val name:String="",
                     val imageUrl:String="",
                     val manufacturer:String="",
                     val coolingType:String="",
                     val coolerForm:String="",
                     val height:Int=0,
                     val length:Int=0,
                     val width:Int=0,
                     val TDP:Int=0){
}
