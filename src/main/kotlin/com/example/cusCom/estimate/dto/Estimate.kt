package com.example.cusCom.estimate.dto

import com.example.cusCom.estimate.entity.EstimateEntity

data class Estimate(var userName: String,
                    var cpu:String,
                    var motherBoard:String,
                    var memory:String,
                    var dataStorage:String,
                    var graphicsCard:String,
                    var cpuCooler:String,
                    var powerSupply:String,
                    var case:String){
    fun toEstimateEntity():EstimateEntity{
        return EstimateEntity(userName,cpu,motherBoard,memory,dataStorage,graphicsCard,cpuCooler,powerSupply,case)
    }
}
