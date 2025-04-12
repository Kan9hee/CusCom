package com.example.cusCom.controller

import com.example.cusCom.component.JsonEditComponent
import com.example.cusCom.config.InnerStringsConfig
import com.example.cusCom.dto.parts.*
import com.example.cusCom.service.DesktopPartsService
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/CusCom/API/admin")
class AdminRestController(private val desktopPartsService: DesktopPartsService,
                          private val jsonEditComponent: JsonEditComponent,
                          private val innerStringsConfig: InnerStringsConfig) {

    @PostMapping("/createParts")
    fun createParts(
        @RequestPart("partsType") partsType: String,
        @RequestPart("requestJSON") requestJSON: String,
        @RequestPart("partsImage") partsImage: MultipartFile?
    ): ResponseEntity<String> {
        val editedJson = jsonEditComponent.uploadImageAndInjectUrl(requestJSON,partsImage)
        when(partsType){
            innerStringsConfig.parts.case->
                desktopPartsService.createCase(Gson().fromJson(editedJson, CaseDTO::class.java))
            innerStringsConfig.parts.cpu->
                desktopPartsService.createCPU(Gson().fromJson(editedJson, CpuDTO::class.java))
            innerStringsConfig.parts.cpuCooler->
                desktopPartsService.createCPUCooler(Gson().fromJson(editedJson, CpuCoolerDTO::class.java))
            innerStringsConfig.parts.dataStorage->
                desktopPartsService.createDataStorage(Gson().fromJson(editedJson, DataStorageDTO::class.java))
            innerStringsConfig.parts.graphicsCard->
                desktopPartsService.createGraphicsCard(Gson().fromJson(editedJson, GraphicsCardDTO::class.java))
            innerStringsConfig.parts.memory->
                desktopPartsService.createMemory(Gson().fromJson(editedJson, MemoryDTO::class.java))
            innerStringsConfig.parts.motherBoard->
                desktopPartsService.createMotherBoard(Gson().fromJson(editedJson, MotherBoardDTO::class.java))
            innerStringsConfig.parts.powerSupply->
                desktopPartsService.createPowerSupply(Gson().fromJson(editedJson, PowerSupplyDTO::class.java))
        }
        return ResponseEntity.ok(innerStringsConfig.property.responseOk)
    }

    @PostMapping("/updateParts")
    fun updateParts(
        @RequestPart("partsType") partsType: String,
        @RequestPart("requestJSON") requestJSON: String,
        @RequestPart("partsImage") partsImage: MultipartFile?,
        @RequestPart("beforePartsName") beforePartsName: String?
    ): ResponseEntity<String> {
        val editedJson = if (partsImage!=null)
            jsonEditComponent.uploadImageAndInjectUrl(requestJSON,partsImage)
        else
            requestJSON

        when(partsType){
            innerStringsConfig.parts.case->
                desktopPartsService.updateCase(Gson().fromJson(editedJson, CaseDTO::class.java),beforePartsName)
            innerStringsConfig.parts.cpu->
                desktopPartsService.updateCPU(Gson().fromJson(editedJson, CpuDTO::class.java),beforePartsName)
            innerStringsConfig.parts.cpuCooler->
                desktopPartsService.updateCPUCooler(Gson().fromJson(editedJson, CpuCoolerDTO::class.java),beforePartsName)
            innerStringsConfig.parts.dataStorage->
                desktopPartsService.updateDataStorage(Gson().fromJson(editedJson, DataStorageDTO::class.java),beforePartsName)
            innerStringsConfig.parts.graphicsCard->
                desktopPartsService.updateGraphicsCard(Gson().fromJson(editedJson, GraphicsCardDTO::class.java),beforePartsName)
            innerStringsConfig.parts.memory->
                desktopPartsService.updateMemory(Gson().fromJson(editedJson, MemoryDTO::class.java),beforePartsName)
            innerStringsConfig.parts.motherBoard->
                desktopPartsService.updateMotherBoard(Gson().fromJson(editedJson, MotherBoardDTO::class.java),beforePartsName)
            innerStringsConfig.parts.powerSupply->
                desktopPartsService.updatePowerSupply(Gson().fromJson(editedJson, PowerSupplyDTO::class.java),beforePartsName)
        }
        return ResponseEntity.ok(innerStringsConfig.property.responseOk)
    }

    @PostMapping("/deleteParts")
    @ResponseBody
    fun deleteParts(@RequestBody requestJSON:String): ResponseEntity<String> {
        val request= ObjectMapper().readTree(java.net.URLDecoder.decode(requestJSON,innerStringsConfig.request.encoding))
        when(request[innerStringsConfig.request.partsType].asText()){
            innerStringsConfig.parts.case->
                desktopPartsService.deleteCase(request[innerStringsConfig.request.targetName].asText())
            innerStringsConfig.parts.cpu->
                desktopPartsService.deleteCPU(request[innerStringsConfig.request.targetName].asText())
            innerStringsConfig.parts.cpuCooler->
                desktopPartsService.deleteCPUCooler(request[innerStringsConfig.request.targetName].asText())
            innerStringsConfig.parts.dataStorage->
                desktopPartsService.deleteDataStorage(request[innerStringsConfig.request.targetName].asText())
            innerStringsConfig.parts.graphicsCard->
                desktopPartsService.deleteGraphicsCard(request[innerStringsConfig.request.targetName].asText())
            innerStringsConfig.parts.memory->
                desktopPartsService.deleteMemory(request[innerStringsConfig.request.targetName].asText())
            innerStringsConfig.parts.motherBoard->
                desktopPartsService.deleteMotherBoard(request[innerStringsConfig.request.targetName].asText())
            innerStringsConfig.parts.powerSupply->
                desktopPartsService.deletePowerSupply(request[innerStringsConfig.request.targetName].asText())
        }
        return ResponseEntity.ok(innerStringsConfig.property.responseOk)
    }
}