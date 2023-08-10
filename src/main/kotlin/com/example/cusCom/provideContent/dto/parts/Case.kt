package com.example.cusCom.provideContent.dto.parts

data class Case(val name:String,
                val manufacturer:String,
                val caseType:String,
                val maxMotherBoard:String,
                val maxCoolingFan:Int,
                val builtInCoolingFan:Int,
                val height:Int,
                val length:Int,
                val width:Int,
                val powerLength:Int,
                val cpuCoolerHeight:Int,
                val graphicsCardLength:Int,
                val imageUrl:String){
}