package com.example.cusCom.userEstimate.dto

import com.example.cusCom.provideContent.dto.parts.*

data class Estimate(var userName: String,
                    var cpu: CPU,
                    var motherBoard:MotherBoard,
                    var memory:Memory,
                    var dataStorage:DataStorage,
                    var graphicsCard:GraphicsCard,
                    var cpuCooler:CPUCooler,
                    var powerSupply:PowerSupply,
                    var case:Case){
}