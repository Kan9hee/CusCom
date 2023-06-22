package com.example.cusCom.estimate.model.parts

data class DataStorage(val name:String,
                       val manufacturer:String,
                       val cpuInterface:String,
                       val formFactor:String,
                       val capacity:Int,
                       val readSpeed:Int,
                       val writeSpeed:Int){}
