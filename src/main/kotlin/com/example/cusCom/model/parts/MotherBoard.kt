package com.example.cusCom.model.parts

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@NoArgsConstructor
@AllArgsConstructor
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
