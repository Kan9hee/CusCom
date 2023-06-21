package com.example.cusCom.model.parts

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@NoArgsConstructor
@AllArgsConstructor
data class GraphicsCard(val name:String,
                        val manufacturer:String,
                        val chipsetManufacturer:String,
                        val gpuType:String,
                        val length:Int,
                        val boastClock:Int,
                        val memory:Int,
                        val basicPower:Int,
                        val maxPower:Int,
                        val phase:Int){}
