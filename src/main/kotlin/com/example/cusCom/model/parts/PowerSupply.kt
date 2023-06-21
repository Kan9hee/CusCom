package com.example.cusCom.model.parts

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@NoArgsConstructor
@AllArgsConstructor
data class PowerSupply(val name:String,
                       val manufacturer:String,
                       val power:Int,
                       val efficiency:String,
                       val modular:String,
                       val length:Int,
                       val warranty:Int){}
