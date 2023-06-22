package com.example.cusCom.estimate.model.parts

data class CPU(val name:String,
               val manufacturer:String,
               val socket:String,
               val memoryType:String,
               val core:Int,
               val thread:Int,
               val isBuiltInGraphics:Boolean,
               val builtInGraphicName:String,
               val TDP:Int){}
