package com.example.cusCom.dto.parts

data class MotherBoardDTO(val name:String,
                          val imageUrl:String?,
                          val manufacturer:String,
                          val cpuType:String,
                          val socket:String,
                          val chipset:String,
                          val motherBoardFormFactor:String,
                          val memoryType:String,
                          val memorySlot:Int,
                          val ssdM2Slot:Int,
                          val ssdSATASlot:Int)
