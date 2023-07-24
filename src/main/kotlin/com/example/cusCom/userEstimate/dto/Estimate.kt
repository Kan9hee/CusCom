package com.example.cusCom.userEstimate.dto

import com.example.cusCom.provideContent.dto.parts.*
import com.example.cusCom.userEstimate.entity.EstimateEntity

data class Estimate(var userName: String,
                    var cpu: CPU,
                    var motherBoard:MotherBoard,
                    var memory:Memory,
                    var dataStorage:DataStorage,
                    var graphicsCard:GraphicsCard,
                    var cpuCooler:CPUCooler,
                    var powerSupply:PowerSupply,
                    var case:Case){

    fun toEstimateEntity():EstimateEntity{
        return EstimateEntity(userName,cpu,motherBoard,memory,dataStorage,graphicsCard,cpuCooler,powerSupply,case)
    }
}