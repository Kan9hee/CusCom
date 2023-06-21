package com.example.cusCom.model.parts

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@NoArgsConstructor
@AllArgsConstructor
data class CPU(val name:String,
               val manufacturer:String,
               val socket:String,
               val memoryType:String,
               val core:Int,
               val thread:Int,
               val isBuiltInGraphics:Boolean,
               val builtInGraphicName:String,
               val TDP:Int){}
