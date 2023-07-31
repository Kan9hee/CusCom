package com.example.cusCom.userEstimate.entity

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "user-estimates")
class EstimateEntity(val _id: ObjectId,
                     val userName:String,
                     cpuName:String,
                     motherBoardName:String,
                     memoryName:String,
                     dataStorageName:String,
                     graphicsCardName:String,
                     cpuCoolerName:String,
                     powerSupplyName:String,
                     caseName:String) {

    var cpu:String=cpuName
        protected set
    var motherBoard:String=motherBoardName
        protected set
    var memory:String=memoryName
        protected set
    var dataStorage:String=dataStorageName
        protected set
    var graphicsCard:String=graphicsCardName
        protected set
    var cpuCooler:String=cpuCoolerName
        protected set
    var powerSupply:String=powerSupplyName
        protected set
    var case:String=caseName
        protected set
}