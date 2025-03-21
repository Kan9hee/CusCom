package com.example.cusCom.dto.parts

data class CpuDTO(val name:String,
                  val imageUrl:String,
                  val manufacturer:String,
                  val socket:String,
                  val memoryType:String,
                  val core:Int,
                  val thread:Int,
                  val isBuiltInGraphics:Boolean,
                  val builtInGraphicName:String?,
                  val tdp:Int)
