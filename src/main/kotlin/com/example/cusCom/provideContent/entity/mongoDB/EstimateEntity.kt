package com.example.cusCom.provideContent.entity.mongoDB

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "user-estimates")
class EstimateEntity(val _id: ObjectId,
                     val userName:String,
                     cpu:String,
                     motherBoard:String,
                     memory:String,
                     dataStorage:String,
                     graphicsCard:String,
                     cpuCooler:String,
                     powerSupply:String,
                     case:String) {

    var cpu:String=cpu
        protected set
    var motherBoard:String=motherBoard
        protected set
    var memory:String=memory
        protected set
    var dataStorage:String=dataStorage
        protected set
    var graphicsCard:String=graphicsCard
        protected set
    var cpuCooler:String=cpuCooler
        protected set
    var powerSupply:String=powerSupply
        protected set
    var case:String=case
        protected set
}