package com.example.cusCom.controller

import com.example.cusCom.provideContent.dto.parts.*
import com.example.cusCom.provideContent.service.DesktopPartsService
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/adminPage")
class AdminController(private val desktopPartsService: DesktopPartsService) {

    @GetMapping("/main")
    fun mainPage(model: Model):String{
        model.addAttribute("caseList",desktopPartsService.getCaseList())
        model.addAttribute("cpuCoolerList",desktopPartsService.getCpuCoolerList())
        model.addAttribute("cpuList",desktopPartsService.getCPUList())
        model.addAttribute("dataStorageList",desktopPartsService.getDataStorageList())
        model.addAttribute("graphicsCardList",desktopPartsService.getGraphicsCardList())
        model.addAttribute("memoryList",desktopPartsService.getMemoryList())
        model.addAttribute("motherBoardList",desktopPartsService.getMotherBoardList())
        model.addAttribute("powerSupplyList",desktopPartsService.getPowerSupplyList())
        return "adminMainPage"
    }

    @GetMapping("/editCase")
    fun editCaseData(@RequestParam("Case") data:String?, model:Model):String{
        if(data!=null) {
            model.addAttribute("item", desktopPartsService.findCase(data))
            return "editPages/caseEditPage"
        }
        return "createPages/caseCreatePage"
    }

    @GetMapping("/editCPU")
    fun editCPUData(@RequestParam("CPU") data:String?, model:Model):String{
        if(data!=null) {
            model.addAttribute("item", desktopPartsService.findCpu(data))
            return "editPages/cpuEditPage"
        }
        return "createPages/cpuCreatePage"
    }

    @GetMapping("/editCPUCooler")
    fun editCPUCoolerData(@RequestParam("CPUCooler") data:String?, model:Model):String{
        if(data!=null) {
            model.addAttribute("item", desktopPartsService.findCpuCooler(data))
            return "editPages/cpuCoolerEditPage"
        }
        return "createPages/cpuCoolerCreatePage"
    }

    @GetMapping("/editDataStorage")
    fun editDataStorageData(@RequestParam("DataStorage") data:String?, model:Model):String{
        if(data!=null) {
            model.addAttribute("item", desktopPartsService.findDataStorage(data))
            return "editPages/dataStorageEditPage"
        }
        return "createPages/dataStorageCreatePage"
    }

    @GetMapping("/editGraphicsCard")
    fun editGraphicsCardData(@RequestParam("GraphicsCard") data:String?, model:Model):String{
        if(data!=null) {
            model.addAttribute("item", desktopPartsService.findGraphicsCard(data))
            return "editPages/graphicsCardEditPage"
        }
        return "createPages/graphicsCardCreatePage"
    }

    @GetMapping("/editMemory")
    fun editMemoryData(@RequestParam("Memory") data:String?, model:Model):String{
        if(data!=null) {
            model.addAttribute("item", desktopPartsService.findMemory(data))
            return "editPages/memoryEditPage"
        }
        return "createPages/memoryCreatePage"
    }

    @GetMapping("/editMotherBoard")
    fun editMotherBoardData(@RequestParam("MotherBoard") data:String?, model:Model):String{
        if(data!=null) {
            model.addAttribute("item", desktopPartsService.findMotherBoard(data))
            return "editPages/motherBoardEditPage"
        }
        return "createPages/motherBoardCreatePage"
    }

    @GetMapping("/editPowerSupply")
    fun editPowerSupplyData(@RequestParam("PowerSupply") data:String?, model:Model):String{
        if(data!=null) {
            model.addAttribute("item", desktopPartsService.findPowerSupply(data))
            return "editPages/powerSupplyEditPage"
        }
        return "createPages/powerSupplyCreatePage"
    }

    @PostMapping("/createParts")
    fun createParts(@RequestParam("Type") type:String,
                  @RequestParam("Data") requestJSON:String): ResponseEntity<String> {
        when(type){
            "Case"->desktopPartsService.createCase(Gson().fromJson(requestJSON, Case::class.java))
            "CPU"->desktopPartsService.createCPU(Gson().fromJson(requestJSON, CPU::class.java))
            "CPUCooler"->desktopPartsService.createCPUCooler(Gson().fromJson(requestJSON, CPUCooler::class.java))
            "DataStorage"->desktopPartsService.createDataStorage(Gson().fromJson(requestJSON, DataStorage::class.java))
            "GraphicsCard"->desktopPartsService.createGraphicsCard(Gson().fromJson(requestJSON, GraphicsCard::class.java))
            "Memory"->desktopPartsService.createMemory(Gson().fromJson(requestJSON, Memory::class.java))
            "MotherBoard"->desktopPartsService.createMotherBoard(Gson().fromJson(requestJSON, MotherBoard::class.java))
            "PowerSupply"->desktopPartsService.createPowerSupply(Gson().fromJson(requestJSON, PowerSupply::class.java))
        }
        return ResponseEntity.ok("Success")
    }

    @PostMapping("/updateParts")
    fun updateParts(@RequestParam("Type") type:String,
                  @RequestParam("Data") requestJSON:String): ResponseEntity<String> {
        when(type){
            "Case"->desktopPartsService.updateCase(Gson().fromJson(requestJSON, Case::class.java))
            "CPU"->desktopPartsService.updateCPU(Gson().fromJson(requestJSON, CPU::class.java))
            "CPUCooler"->desktopPartsService.updateCPUCooler(Gson().fromJson(requestJSON, CPUCooler::class.java))
            "DataStorage"->desktopPartsService.updateDataStorage(Gson().fromJson(requestJSON, DataStorage::class.java))
            "GraphicsCard"->desktopPartsService.updateGraphicsCard(Gson().fromJson(requestJSON, GraphicsCard::class.java))
            "Memory"->desktopPartsService.updateMemory(Gson().fromJson(requestJSON, Memory::class.java))
            "MotherBoard"->desktopPartsService.updateMotherBoard(Gson().fromJson(requestJSON, MotherBoard::class.java))
            "PowerSupply"->desktopPartsService.updatePowerSupply(Gson().fromJson(requestJSON, PowerSupply::class.java))
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
}