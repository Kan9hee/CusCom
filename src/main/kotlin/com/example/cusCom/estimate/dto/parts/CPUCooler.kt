package com.example.cusCom.estimate.dto.parts

data class CPUCooler(val name:String,
                     val manufacturer:String,
                     val coolingType:String,
                     val coolerForm:String,
                     val height:Int,
                     val length:Int,
                     val width:Int,
                     val TDP:Int){

    fun toCPUCoolerEntity():CPUCoolerEntity{
        return CPUCoolerEntity(name, manufacturer, coolingType, coolerForm, height, length, width, TDP)
    }
}
