package com.example.cusCom.userEstimate.service

import com.example.cusCom.userEstimate.dto.Estimate
import com.example.cusCom.provideContent.dto.parts.*
import com.example.cusCom.provideContent.service.DesktopPartsService
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class EstimateDeserializer(private val desktopPartsService: DesktopPartsService):JsonDeserializer<Estimate> {

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Estimate {
        val userName=json.asJsonObject.get("userName").asString
        val cpu:CPU = desktopPartsService.findCpu(json.asJsonObject.get("cpu").asString)
        val motherBoard:MotherBoard=desktopPartsService.findMotherBoard(json.asJsonObject.get("motherBoard").asString)
        val memory:Memory=desktopPartsService.findMemory(json.asJsonObject.get("memory").asString)
        val dataStorage:DataStorage=desktopPartsService.findDataStorage(json.asJsonObject.get("dataStorage").asString)
        val graphicsCard:GraphicsCard=desktopPartsService.findGraphicsCard(json.asJsonObject.get("graphicsCard").asString)
        val cpuCooler:CPUCooler=desktopPartsService.findCpuCooler(json.asJsonObject.get("cpuCooler").asString)
        val powerSupply:PowerSupply=desktopPartsService.findPowerSupply(json.asJsonObject.get("powerSupply").asString)
        val desktopCase:Case=desktopPartsService.findCase(json.asJsonObject.get("desktopCase").asString)

        return Estimate(userName,cpu,motherBoard,memory,dataStorage,graphicsCard,cpuCooler,powerSupply,desktopCase)
    }
}