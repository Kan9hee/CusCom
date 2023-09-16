package com.example.cusCom.provideContent.dto.parts

data class GraphicsCard(val name:String="",
                        val imageUrl:String="",
                        val manufacturer:String="",
                        val chipsetManufacturer:String="NVIDIA",
                        val gpuType:String="",
                        val length:Int=0,
                        val basicPower:Int=0,
                        val maxPower:Int=0,
                        val phase:Int=0){
}
