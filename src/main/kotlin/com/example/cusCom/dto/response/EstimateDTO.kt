package com.example.cusCom.dto.response

data class EstimateDTO(var _id:String,
                       var userName: String,
                       var posted:Boolean,
                       var cpu: String,
                       var motherBoard:String,
                       var memory:String,
                       var dataStorage:String,
                       var graphicsCard:String,
                       var cpuCooler:String,
                       var powerSupply:String,
                       var desktopCase:String)