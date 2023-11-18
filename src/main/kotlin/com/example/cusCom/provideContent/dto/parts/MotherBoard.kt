package com.example.cusCom.provideContent.dto.parts

import com.example.cusCom.provideContent.dto.MotherBoardFormFactor

data class MotherBoard(val id:Long?=null,
                       val name:String="",
                       val imageUrl:String="",
                       val manufacturer:String="",
                       val cpuType:String="",
                       val socket:String="",
                       val chipset:String="",
                       val motherBoardFormFactor: MotherBoardFormFactor=MotherBoardFormFactor(),
                       val memoryType:String="",
                       val memorySlot:Int=0,
                       val ssdM2Slot:Int=0,
                       val ssdSATASlot:Int=0){
}
