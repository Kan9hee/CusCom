package com.example.cusCom.provideContent.entity.mongoDB

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "\${dbString.mysql.collection.estimate}")
class EstimateEntity(val _id: ObjectId,
                     val userName:String,
                     posted:Boolean,
                     cpu:String,
                     motherBoard:String,
                     memory:String,
                     dataStorage:String,
                     graphicsCard:String,
                     cpuCooler:String,
                     powerSupply:String,
                     desktopCase:String) {

    var posted:Boolean=posted
        protected set
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
    var desktopCase:String=desktopCase
        protected set
}