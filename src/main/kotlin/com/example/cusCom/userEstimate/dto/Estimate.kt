package com.example.cusCom.userEstimate.dto

data class Estimate(var _id:String,
                    var userName: String,
                    var cpu: String,
                    var motherBoard:String,
                    var memory:String,
                    var dataStorage:String,
                    var graphicsCard:String,
                    var cpuCooler:String,
                    var powerSupply:String,
                    var case:String){
}