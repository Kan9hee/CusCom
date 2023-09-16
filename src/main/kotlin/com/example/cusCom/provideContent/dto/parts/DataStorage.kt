package com.example.cusCom.provideContent.dto.parts

data class DataStorage(val name:String="",
                       val imageUrl:String="",
                       val manufacturer:String="",
                       val storageInterface:String="PCIe 4.0(x4)",
                       val formFactor:String="M.2 2280",
                       val capacity:String="256GB",
                       val readSpeed:Int=0,
                       val writeSpeed:Int=0){
}
