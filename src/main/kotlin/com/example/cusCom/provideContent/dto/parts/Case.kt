package com.example.cusCom.provideContent.dto.parts

import com.example.cusCom.provideContent.dto.MotherBoardFormFactor

data class Case(val id:Long?=null,
                val name:String="",
                val manufacturer:String="",
                val caseType:String="",
                val motherBoardFormFactor: MotherBoardFormFactor=MotherBoardFormFactor(),
                val maxCoolingFan:Int=0,
                val builtInCoolingFan:Int=0,
                val height:Int=0,
                val length:Int=0,
                val width:Int=0,
                val powerLength:Int=0,
                val cpuCoolerHeight:Int=0,
                val graphicsCardLength:Int=0,
                val imageUrl:String=""){
}
