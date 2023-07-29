package com.example.cusCom.userEstimate.entity

import com.example.cusCom.userEstimate.dto.Estimate
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "user-estimates")
class EstimateEntity(var _id: ObjectId,
                     var userName:String,
                     var cpu:String,
                     var motherBoard:String,
                     var memory:String,
                     var dataStorage:String,
                     var graphicsCard:String,
                     var cpuCooler:String,
                     var powerSupply:String,
                     var case:String) {
    fun convert(estimate: Estimate) {
        var userName:String=estimate.userName
        var cpu:String=estimate.cpu.name
        var motherBoard:String=estimate.motherBoard.name
        var memory:String=estimate.memory.name
        var dataStorage:String=estimate.dataStorage.name
        var graphicsCard:String=estimate.graphicsCard.name
        var cpuCooler:String=estimate.cpuCooler.name
        var powerSupply:String=estimate.powerSupply.name
        var case:String=estimate.case.name
    }
}