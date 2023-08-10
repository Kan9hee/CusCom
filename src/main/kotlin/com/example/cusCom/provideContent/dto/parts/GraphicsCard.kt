package com.example.cusCom.provideContent.dto.parts

data class GraphicsCard(val name:String,
                        val imageUrl:String,
                        val manufacturer:String,
                        val chipsetManufacturer:String,
                        val gpuType:String,
                        val length:Int,
                        val basicPower:Int,
                        val maxPower:Int,
                        val phase:Int){
}