package com.example.cusCom.model.parts

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@NoArgsConstructor
@AllArgsConstructor
data class Case(val name:String,
                val manufacturer:String,
                val caseType:String,
                val maxMotherBoard:String,
                val maxCoolingFan:Int,
                val builtInCoolingFan:Int,
                val ssdSocket:Int,
                val hddSocket:Int,
                val height:Int,
                val length:Int,
                val width:Int,
                val powerLength:Int,
                val cpuCoolerHeight:Int,
                val graphicsCardLength:Int){}
