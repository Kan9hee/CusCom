package com.example.cusCom.provideContent.dto.parts

data class DataStorage(val name:String,
                       val manufacturer:String,
                       val storageInterface:String,
                       val formFactor:String,
                       val capacity:String,
                       val readSpeed:Int,
                       val writeSpeed:Int){

    fun toDataStorageEntity():DataStorageEntity{
        return DataStorageEntity(name, manufacturer, storageInterface, formFactor, capacity, readSpeed, writeSpeed)
    }
}
