package com.example.cusCom.provideContent.dto.parts

data class CPU(val name:String,
               val manufacturer:String,
               val socket:String,
               val memoryType:String,
               val core:Int,
               val thread:Int,
               val isBuiltInGraphics:Boolean,
               val builtInGraphicName:String?,
               val TDP:Int){

    fun toCPUEntity():CPUEntity{
        return CPUEntity(name,manufacturer,socket,memoryType,core,thread,isBuiltInGraphics,builtInGraphicName,TDP)
    }
}
