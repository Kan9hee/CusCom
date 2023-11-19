package com.example.cusCom.controller

import com.example.cusCom.config.InnerStringsConfig
import com.example.cusCom.exception.CusComErrorCode
import com.example.cusCom.exception.CusComException
import com.example.cusCom.provideContent.dto.parts.*
import com.example.cusCom.provideContent.service.BlobService
import com.example.cusCom.provideContent.service.DesktopPartsService
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/CusCom/adminAPI")
class AdminRestController(private val desktopPartsService: DesktopPartsService,
                          private val blobService: BlobService,
                          private val innerStringsConfig: InnerStringsConfig) {

    @PostMapping("/createParts")
    fun createParts(@RequestParam("Type") type:String,
                    @RequestParam("Data") requestJSON:String,
                    @RequestParam("Image") image: MultipartFile
    ): ResponseEntity<String> {
        val editedJson = editJson(requestJSON,type,image)
        when(type){
            innerStringsConfig.parts.case->
                desktopPartsService.createCase(Gson().fromJson(editedJson, Case::class.java))
            innerStringsConfig.parts.cpu->
                desktopPartsService.createCPU(Gson().fromJson(editedJson, CPU::class.java))
            innerStringsConfig.parts.cpuCooler->
                desktopPartsService.createCPUCooler(Gson().fromJson(editedJson, CPUCooler::class.java))
            innerStringsConfig.parts.dataStorage->
                desktopPartsService.createDataStorage(Gson().fromJson(editedJson, DataStorage::class.java))
            innerStringsConfig.parts.graphicsCard->
                desktopPartsService.createGraphicsCard(Gson().fromJson(editedJson, GraphicsCard::class.java))
            innerStringsConfig.parts.memory->
                desktopPartsService.createMemory(Gson().fromJson(editedJson, Memory::class.java))
            innerStringsConfig.parts.motherBoard->
                desktopPartsService.createMotherBoard(Gson().fromJson(editedJson, MotherBoard::class.java))
            innerStringsConfig.parts.powerSupply->
                desktopPartsService.createPowerSupply(Gson().fromJson(editedJson, PowerSupply::class.java))
        }
        return ResponseEntity.ok(innerStringsConfig.property.responseOk)
    }

    @PostMapping("/updateParts")
    fun updateParts(@RequestParam("Type") type:String,
                    @RequestParam("Data") requestJSON:String,
                    @RequestParam("Image") image:MultipartFile,
                    @RequestParam("BeforeID") BeforeID:Long
    ): ResponseEntity<String> {
        val editedJson = editJson(requestJSON,type,image)
        when(type){
            innerStringsConfig.parts.case->
                desktopPartsService.updateCase(Gson().fromJson(editedJson, Case::class.java),BeforeID)
            innerStringsConfig.parts.cpu->
                desktopPartsService.updateCPU(Gson().fromJson(editedJson, CPU::class.java),BeforeID)
            innerStringsConfig.parts.cpuCooler->
                desktopPartsService.updateCPUCooler(Gson().fromJson(editedJson, CPUCooler::class.java),BeforeID)
            innerStringsConfig.parts.dataStorage->
                desktopPartsService.updateDataStorage(Gson().fromJson(editedJson, DataStorage::class.java),BeforeID)
            innerStringsConfig.parts.graphicsCard->
                desktopPartsService.updateGraphicsCard(Gson().fromJson(editedJson, GraphicsCard::class.java),BeforeID)
            innerStringsConfig.parts.memory->
                desktopPartsService.updateMemory(Gson().fromJson(editedJson, Memory::class.java),BeforeID)
            innerStringsConfig.parts.motherBoard->
                desktopPartsService.updateMotherBoard(Gson().fromJson(editedJson, MotherBoard::class.java),BeforeID)
            innerStringsConfig.parts.powerSupply->
                desktopPartsService.updatePowerSupply(Gson().fromJson(editedJson, PowerSupply::class.java),BeforeID)
        }
        return ResponseEntity.ok(innerStringsConfig.property.responseOk)
    }

    @PostMapping("/deleteParts")
    @ResponseBody
    fun deleteParts(@RequestBody requestJSON:String): ResponseEntity<String> {
        val request= ObjectMapper().readTree(java.net.URLDecoder.decode(requestJSON,innerStringsConfig.request.encoding))
        when(request[innerStringsConfig.request.partsType].asText()){
            innerStringsConfig.parts.case->
                desktopPartsService.deleteCase(request[innerStringsConfig.request.targetID].asText().toLong())
            innerStringsConfig.parts.cpu->
                desktopPartsService.deleteCPU(request[innerStringsConfig.request.targetID].asText().toLong())
            innerStringsConfig.parts.cpuCooler->
                desktopPartsService.deleteCPUCooler(request[innerStringsConfig.request.targetID].asText().toLong())
            innerStringsConfig.parts.dataStorage->
                desktopPartsService.deleteDataStorage(request[innerStringsConfig.request.targetID].asText().toLong())
            innerStringsConfig.parts.graphicsCard->
                desktopPartsService.deleteGraphicsCard(request[innerStringsConfig.request.targetID].asText().toLong())
            innerStringsConfig.parts.memory->
                desktopPartsService.deleteMemory(request[innerStringsConfig.request.targetID].asText().toLong())
            innerStringsConfig.parts.motherBoard->
                desktopPartsService.deleteMotherBoard(request[innerStringsConfig.request.targetID].asText().toLong())
            innerStringsConfig.parts.powerSupply->
                desktopPartsService.deletePowerSupply(request[innerStringsConfig.request.targetID].asText().toLong())
        }
        return ResponseEntity.ok(innerStringsConfig.property.responseOk)
    }

    private fun editJson(json:String,type:String,image:MultipartFile): String {
        var jsonObject = Gson().fromJson(json, JsonObject::class.java)
        val contentType=image.contentType
        if (contentType != null && contentType.startsWith("image/")) {
            jsonObject.addProperty(innerStringsConfig.property.imageUrl, blobService
                .uploadImage(image, innerStringsConfig.property.imageWidth, innerStringsConfig.property.imageHeight)
            )
            if (type == innerStringsConfig.parts.case || type == innerStringsConfig.parts.motherBoard)
                deserializeFormFactor(jsonObject)
        }
        else
            throw CusComException(CusComErrorCode.NotImageData)
        return Gson().toJson(jsonObject)
    }

    private fun deserializeFormFactor(jsonObject: JsonObject) {
        val formFactorName:String = jsonObject.get(innerStringsConfig.property.formFactor).toString().removeSurrounding("\"")
        val formFactorObject=Gson().toJsonTree(desktopPartsService.findMotherBoardFormFactor(formFactorName)).asJsonObject
        jsonObject.add(innerStringsConfig.property.formFactor,formFactorObject)
    }
}