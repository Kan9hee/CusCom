package com.example.cusCom.dto.parts

data class PowerSupplyDTO(val name:String,
                          val imageUrl:String?,
                          val manufacturer:String,
                          val power:Int,
                          val efficiency:String,
                          val modular:String,
                          val length:Int)