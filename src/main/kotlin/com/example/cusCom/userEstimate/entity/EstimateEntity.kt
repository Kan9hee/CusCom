package com.example.cusCom.userEstimate.entity

import com.example.cusCom.provideContent.dto.parts.*
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "user-estimates")
class EstimateEntity(userName:String,
                     cpu: CPU,
                     motherBoard: MotherBoard,
                     memory: Memory,
                     dataStorage: DataStorage,
                     graphicsCard: GraphicsCard,
                     cpuCooler: CPUCooler,
                     powerSupply: PowerSupply,
                     case: Case) {
    val userName:String=userName
    val cpu:String=cpu.name
    val motherBoard:String=motherBoard.name
    val memory:String=memory.name
    val dataStorage:String=dataStorage.name
    val graphicsCard:String=graphicsCard.name
    val cpuCooler:String=cpuCooler.name
    val powerSupply:String=powerSupply.name
    val case:String=case.name
}