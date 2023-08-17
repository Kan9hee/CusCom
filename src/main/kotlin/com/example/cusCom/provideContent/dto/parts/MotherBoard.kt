package com.example.cusCom.provideContent.dto.parts

import com.example.cusCom.provideContent.dto.MotherBoardFormFactor

data class MotherBoard(val name:String,
                       val imageUrl:String,
                       val manufacturer:String,
                       val cpuType:String,
                       val socket:String,
                       val chipset:String,
                       val formFactor: MotherBoardFormFactor,
                       val memoryType:String,
                       val memorySlot:Int,
                       val ssdM2Slot:Int,
                       val ssdSATASlot:Int){
}
