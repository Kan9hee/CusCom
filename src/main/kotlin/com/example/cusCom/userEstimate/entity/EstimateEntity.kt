package com.example.cusCom.userEstimate.entity

import com.example.cusCom.userEstimate.dto.Estimate
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "user-estimates")
class EstimateEntity(estimate: Estimate) {
    val userName:String=estimate.userName
    val cpu:String=estimate.cpu.name
    val motherBoard:String=estimate.motherBoard.name
    val memory:String=estimate.memory.name
    val dataStorage:String=estimate.dataStorage.name
    val graphicsCard:String=estimate.graphicsCard.name
    val cpuCooler:String=estimate.cpuCooler.name
    val powerSupply:String=estimate.powerSupply.name
    val case:String=estimate.case.name
}