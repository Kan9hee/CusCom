package com.example.cusCom.model.parts

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@NoArgsConstructor
@AllArgsConstructor
data class CPUCooler(val name:String,
                     val manufacturer:String,
                     val coolingType:String,
                     val color:String,
                     val coolerForm:String,
                     val height:Int,
                     val length:Int,
                     val width:Int,
                     val warranty:Int){}
