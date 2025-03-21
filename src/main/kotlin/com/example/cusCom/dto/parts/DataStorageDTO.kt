package com.example.cusCom.dto.parts

data class DataStorageDTO(val name:String,
                          val imageUrl:String,
                          val manufacturer:String,
                          val storageInterface:String,
                          val formFactor:String,
                          val capacity:String,
                          val readSpeed:Int,
                          val writeSpeed:Int)
