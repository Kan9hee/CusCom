package com.example.cusCom.model.parts

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@NoArgsConstructor
@AllArgsConstructor
data class Memory(val name:String,
                  val manufacturer:String,
                  val type:String,
                  val capacity:Int,
                  val bandwidth:Int,
                  val height:Int){}
