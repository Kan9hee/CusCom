package com.example.cusCom.estimate.model.parts

data class MotherBoard(val name:String,
                       val manufacturer:String,
                       val cpuManufacturer:String,
                       val socket:String,
                       val chipset:String,
                       val formFactor:String,
                       val memoryType:String,
                       val memorySlot:Int,
                       val ssdM2Slot:Int,
                       val ssdSATASlot:Int,
                       val VRM:Int){}
