package com.example.cusCom.controller

import com.example.cusCom.provideContent.dto.parts.*
import com.example.cusCom.provideContent.service.BlobService
import com.example.cusCom.provideContent.service.DesktopPartsService
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/CusCom/adminAPI")
class AdminRestController(private val desktopPartsService: DesktopPartsService,
                          private val blobService: BlobService) {

    @PostMapping("/createParts")
    fun createParts(@RequestParam("Type") type:String,
                    @RequestParam("Data") requestJSON:String,
                    @RequestParam("Image") image: MultipartFile
    ): ResponseEntity<String> {
        val editedJson = editJson(requestJSON,type,image)
        when(type){
            "Case"->desktopPartsService.createCase(Gson().fromJson(editedJson, Case::class.java))
            "CPU"->desktopPartsService.createCPU(Gson().fromJson(editedJson, CPU::class.java))
            "CPUCooler"->desktopPartsService.createCPUCooler(Gson().fromJson(editedJson, CPUCooler::class.java))
            "DataStorage"->desktopPartsService.createDataStorage(Gson().fromJson(editedJson, DataStorage::class.java))
            "GraphicsCard"->desktopPartsService.createGraphicsCard(Gson().fromJson(editedJson, GraphicsCard::class.java))
            "Memory"->desktopPartsService.createMemory(Gson().fromJson(editedJson, Memory::class.java))
            "MotherBoard"->desktopPartsService.createMotherBoard(Gson().fromJson(editedJson, MotherBoard::class.java))
            "PowerSupply"->desktopPartsService.createPowerSupply(Gson().fromJson(editedJson, PowerSupply::class.java))
        }
        return ResponseEntity.ok("Success")
    }

    @PostMapping("/updateParts")
    fun updateParts(@RequestParam("Type") type:String,
                    @RequestParam("Data") requestJSON:String,
                    @RequestParam("Image") image: MultipartFile
    ): ResponseEntity<String> {
        val editedJson = editJson(requestJSON,type,image)
        when(type){
            "Case"->desktopPartsService.updateCase(Gson().fromJson(editedJson, Case::class.java))
            "CPU"->desktopPartsService.updateCPU(Gson().fromJson(editedJson, CPU::class.java))
            "CPUCooler"->desktopPartsService.updateCPUCooler(Gson().fromJson(editedJson, CPUCooler::class.java))
            "DataStorage"->desktopPartsService.updateDataStorage(Gson().fromJson(editedJson, DataStorage::class.java))
            "GraphicsCard"->desktopPartsService.updateGraphicsCard(Gson().fromJson(editedJson, GraphicsCard::class.java))
            "Memory"->desktopPartsService.updateMemory(Gson().fromJson(editedJson, Memory::class.java))
            "MotherBoard"->desktopPartsService.updateMotherBoard(Gson().fromJson(editedJson, MotherBoard::class.java))
            "PowerSupply"->desktopPartsService.updatePowerSupply(Gson().fromJson(editedJson, PowerSupply::class.java))
        }
        return ResponseEntity.ok("Success")
    }

    @PostMapping("/deleteParts")
    @ResponseBody
    fun deleteParts(@RequestBody requestJSON:String): ResponseEntity<String> {
        val request= ObjectMapper().readTree(java.net.URLDecoder.decode(requestJSON, "UTF-8"))
        when(request["type"].asText()){
            "Case"->desktopPartsService.deleteCase(request["name"].asText())
            "CPU"->desktopPartsService.deleteCPU(request["name"].asText())
            "CPUCooler"->desktopPartsService.deleteCPUCooler(request["name"].asText())
            "DataStorage"->desktopPartsService.deleteDataStorage(request["name"].asText())
            "GraphicsCard"->desktopPartsService.deleteGraphicsCard(request["name"].asText())
            "Memory"->desktopPartsService.deleteMemory(request["name"].asText())
            "MotherBoard"->desktopPartsService.deleteMotherBoard(request["name"].asText())
            "PowerSupply"->desktopPartsService.deletePowerSupply(request["name"].asText())
        }
        return ResponseEntity.ok("Success")
    }

    private fun editJson(json:String,type:String,image:MultipartFile): String {
        var jsonObject = Gson().fromJson(json, JsonObject::class.java)
        jsonObject.addProperty("imageUrl",blobService.uploadImage(image))
        if(type == "Case" || type == "MotherBoard")
            deserializeFormFactor(jsonObject)
        return Gson().toJson(jsonObject)
    }

    private fun deserializeFormFactor(jsonObject: JsonObject) {
        val formFactorName:String = jsonObject.get("motherBoardFormFactor").toString().removeSurrounding("\"")
        val formFactorObject=Gson().toJsonTree(desktopPartsService.findMotherBoardFormFactor(formFactorName)).asJsonObject
        jsonObject.add("motherBoardFormFactor",formFactorObject)
    }
}