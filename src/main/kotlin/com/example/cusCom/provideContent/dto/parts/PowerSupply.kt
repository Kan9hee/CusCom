package com.example.cusCom.provideContent.dto.parts

data class PowerSupply(val name:String,
                       val manufacturer:String,
                       val power:Int,
                       val efficiency:String,
                       val modular:String,
                       val length:Int){

    fun toPowerSupplyEntity():PowerSupplyEntity{
        return PowerSupplyEntity(name, manufacturer, power, efficiency, modular, length)
    }
}
