package com.example.cusCom.provideContent.dto.parts

data class CPU(val name:String="",
               val imageUrl:String="",
               val manufacturer:String="",
               val socket:String="AM4",
               val memoryType:String="DDR5",
               val core:Int=0,
               val thread:Int=0,
               val isBuiltInGraphics:Boolean=false,
               val builtInGraphicName:String?=null,
               val TDP:Int=0){
}
