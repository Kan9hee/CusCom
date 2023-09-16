package com.example.cusCom.provideContent.dto.parts

import com.example.cusCom.provideContent.dto.MotherBoardFormFactor

data class Case(val name:String="",
                val manufacturer:String="",
                val caseType:String="빅타워",
                val motherBoardFormFactor: MotherBoardFormFactor=MotherBoardFormFactor("ATX",305,244),
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
