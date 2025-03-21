package com.example.cusCom.dto.parts

data class CaseDTO(val name:String,
                   val manufacturer:String,
                   val caseType:String,
                   val motherBoardFormFactor: String,
                   val maxCoolingFan:Int,
                   val builtInCoolingFan:Int,
                   val height:Int,
                   val length:Int,
                   val width:Int,
                   val powerLength:Int,
                   val cpuCoolerHeight:Int,
                   val graphicsCardLength:Int,
                   val imageUrl:String)
