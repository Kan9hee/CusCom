package com.example.cusCom.sharePlace.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "user-estimates")
class SharePlaceEntity(userName:String,
                       cpu: String,
                       motherBoard: String,
                       memory: String,
                       dataStorage: String,
                       graphicsCard: String,
                       cpuCooler: String,
                       powerSupply: String,
                       desktopCase: String) {
    @Id
    val id:Long=1   //temporary value. it will be change
    val userName:String=userName
    val cpu:String=cpu
    val motherBoard:String=motherBoard
    val memory:String=memory
    val dataStorage:String=dataStorage
    val graphicsCard:String=graphicsCard
    val cpuCooler:String=cpuCooler
    val powerSupply:String=powerSupply
    val desktopCase:String=desktopCase
}