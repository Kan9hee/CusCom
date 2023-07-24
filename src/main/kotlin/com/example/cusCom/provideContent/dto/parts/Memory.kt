package com.example.cusCom.provideContent.dto.parts

data class Memory(val name:String,
                  val manufacturer:String,
                  val type:String,
                  val capacity:Int,
                  val height:Int){

    fun toMemoryEntity():MemoryEntity{
        return MemoryEntity(name, manufacturer, type, capacity, height)
    }
}
