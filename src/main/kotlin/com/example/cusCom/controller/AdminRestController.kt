package com.example.cusCom.controller

import com.example.cusCom.component.JsonEditComponent
import com.example.cusCom.config.InnerStringsConfig
import com.example.cusCom.dto.parts.*
import com.example.cusCom.dto.request.RequestPartsDTO
import com.example.cusCom.service.DesktopPartsService
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/CusCom/admin/API")
class AdminRestController(private val desktopPartsService: DesktopPartsService,
                          private val jsonEditComponent: JsonEditComponent,
                          private val innerStringsConfig: InnerStringsConfig) {

    @PostMapping("/createParts")
    fun createParts(@RequestBody requestPartsDTO: RequestPartsDTO): ResponseEntity<String> {
        val editedJson = jsonEditComponent.uploadImageAndInjectUrl(requestPartsDTO.requestJSON,requestPartsDTO.partsImage)
        when(requestPartsDTO.partsType){
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
    fun updateParts(@RequestBody requestPartsDTO: RequestPartsDTO): ResponseEntity<String> {
        val editedJson = if (requestPartsDTO.partsImage!=null)
            jsonEditComponent.uploadImageAndInjectUrl(requestPartsDTO.requestJSON,requestPartsDTO.partsImage)
        else
            requestPartsDTO.requestJSON

        when(requestPartsDTO.partsType){
            innerStringsConfig.parts.case->
                desktopPartsService.updateCase(Gson().fromJson(editedJson, CaseDTO::class.java),requestPartsDTO.beforePartsName)
            innerStringsConfig.parts.cpu->
                desktopPartsService.updateCPU(Gson().fromJson(editedJson, CpuDTO::class.java),requestPartsDTO.beforePartsName)
            innerStringsConfig.parts.cpuCooler->
                desktopPartsService.updateCPUCooler(Gson().fromJson(editedJson, CpuCoolerDTO::class.java),requestPartsDTO.beforePartsName)
            innerStringsConfig.parts.dataStorage->
                desktopPartsService.updateDataStorage(Gson().fromJson(editedJson, DataStorageDTO::class.java),requestPartsDTO.beforePartsName)
            innerStringsConfig.parts.graphicsCard->
                desktopPartsService.updateGraphicsCard(Gson().fromJson(editedJson, GraphicsCardDTO::class.java),requestPartsDTO.beforePartsName)
            innerStringsConfig.parts.memory->
                desktopPartsService.updateMemory(Gson().fromJson(editedJson, MemoryDTO::class.java),requestPartsDTO.beforePartsName)
            innerStringsConfig.parts.motherBoard->
                desktopPartsService.updateMotherBoard(Gson().fromJson(editedJson, MotherBoardDTO::class.java),requestPartsDTO.beforePartsName)
            innerStringsConfig.parts.powerSupply->
                desktopPartsService.updatePowerSupply(Gson().fromJson(editedJson, PowerSupplyDTO::class.java),requestPartsDTO.beforePartsName)
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