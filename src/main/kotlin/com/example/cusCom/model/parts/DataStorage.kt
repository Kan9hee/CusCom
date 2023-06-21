package com.example.cusCom.model.parts

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@NoArgsConstructor
@AllArgsConstructor
data class DataStorage(val name:String,
                       val manufacturer:String,
                       val cpuInterface:String,
                       val formFactor:String,
                       val capacity:Int,
                       val readSpeed:Int,
                       val writeSpeed:Int){}
