package com.example.cusCom.estimate.model.parts

data class DataStorage(val name:String,
                       val manufacturer:String,
                       val storageInterface:String,
                       val formFactor:String,
                       val capacity:String,
                       val readSpeed:Int,
                       val writeSpeed:Int){}
