package com.example.cusCom.dto.parts

data class CpuCoolerDTO(val name:String,
                        val imageUrl:String?,
                        val manufacturer:String,
                        val coolingType:String,
                        val coolerForm:String,
                        val height:Int,
                        val length:Int,
                        val width:Int,
                        val tdp:Int)
