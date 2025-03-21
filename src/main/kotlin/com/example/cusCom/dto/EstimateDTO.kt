package com.example.cusCom.dto

data class EstimateDTO(var _id:String="",
                       var userName: String="",
                       var posted:Boolean=false,
                       var cpu: String="",
                       var motherBoard:String="",
                       var memory:String="",
                       var dataStorage:String="",
                       var graphicsCard:String="",
                       var cpuCooler:String="",
                       var powerSupply:String="",
                       var desktopCase:String=""){
}