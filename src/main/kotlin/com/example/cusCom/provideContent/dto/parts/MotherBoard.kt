package com.example.cusCom.provideContent.dto.parts

data class MotherBoard(val name:String,
                       val imageUrl:String,
                       val manufacturer:String,
                       val cpuType:String,
                       val socket:String,
                       val chipset:String,
                       val formFactor:String,
                       val memoryType:String,
                       val memorySlot:Int,
                       val ssdM2Slot:Int,
                       val ssdSATASlot:Int){
}