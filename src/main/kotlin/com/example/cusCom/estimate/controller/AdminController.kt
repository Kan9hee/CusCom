package com.example.cusCom.estimate.controller

import com.example.cusCom.estimate.dto.parts.*
import com.example.cusCom.estimate.service.DesktopPartsService
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import net.minidev.json.JSONObject
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
    fun editCaseData():String{
        return "caseEditPage"
    }

    @GetMapping("/editCPU")
    fun editCPUData():String{
        return "cpuEditPage"
    }

    @GetMapping("/editCPUCooler")
    fun editCPUCoolerData():String{
        return "cpuCoolerEditPage"
    }

    @GetMapping("/editDataStorage")
    fun editDataStorageData():String{
        return "dataStorageEditPage"
    }

    @GetMapping("/editGraphicsCard")
    fun editGraphicsCardData():String{
        return "graphicsCardEditPage"
    }

    @GetMapping("/editMemory")
    fun editMemoryData():String{
        return "memoryEditPage"
    }

    @GetMapping("/editMotherBoard")
    fun editMotherBoardData():String{
        return "motherBoardEditPage"
    }

    @GetMapping("/editPowerSupply")
    fun editPowerSupplyData():String{
        return "powerSupplyEditPage"
    }

    @PostMapping("/editCase")
    fun saveCaseData(@RequestParam("Case") caseJSON:String):String{
        desktopPartsService.createCase(Gson().fromJson(caseJSON, Case::class.java))
        return "redirect:/adminPage/main"
    }

    @PostMapping("/editCPU")
    fun saveCPUData(@RequestParam("CPU") cpuJSON:String):String{
        desktopPartsService.createCPU(Gson().fromJson(cpuJSON, CPU::class.java))
        return "redirect:/adminPage/main"
    }

    @PostMapping("/editCPUCooler")
    fun saveCPUCoolerData(@RequestParam("CPUCooler") cpuCoolerJSON:String):String{
        desktopPartsService.createCPUCooler(Gson().fromJson(cpuCoolerJSON, CPUCooler::class.java))
        return "redirect:/adminPage/main"
    }

    @PostMapping("/editDataStorage")
    fun saveDataStorageData(@RequestParam("DataStorage") dataStorageJSON:String):String{
        desktopPartsService.createDataStorage(Gson().fromJson(dataStorageJSON, DataStorage::class.java))
        return "redirect:/adminPage/main"
    }

    @PostMapping("/editGraphicsCard")
    fun saveGraphicsCardData(@RequestParam("GraphicsCard") graphicsCardJSON:String):String{
        desktopPartsService.createGraphicsCard(Gson().fromJson(graphicsCardJSON, GraphicsCard::class.java))
        return "redirect:/adminPage/main"
    }

    @PostMapping("/editMemory")
    fun saveMemoryData(@RequestParam("Memory") memoryJSON:String):String{
        desktopPartsService.createMemory(Gson().fromJson(memoryJSON, Memory::class.java))
        return "redirect:/adminPage/main"
    }

    @PostMapping("/editMotherBoard")
    fun saveMotherBoard(@RequestParam("MotherBoard") motherBoardJSON:String):String{
        desktopPartsService.createMotherBoard(Gson().fromJson(motherBoardJSON, MotherBoard::class.java))
        return "redirect:/adminPage/main"
    }

    @PostMapping("/editPowerSupply")
    fun savePowerSupplyData(@RequestParam("PowerSupply") powerSupplyJSON:String):String{
        desktopPartsService.createPowerSupply(Gson().fromJson(powerSupplyJSON, PowerSupply::class.java))
        return "redirect:/adminPage/main"
    }

    @PostMapping("/deleteParts")
    @ResponseBody
    fun deleteParts(@RequestBody requestJSON:String): ResponseEntity<String> {
        val request= ObjectMapper().readTree(java.net.URLDecoder.decode(requestJSON, "UTF-8"))
        when(request["type"].asText()){
            "case"->desktopPartsService.deleteCase(request["name"].asText())
            "cpu"->desktopPartsService.deleteCPU(request["name"].asText())
            "cpuCooler"->desktopPartsService.deleteCPUCooler(request["name"].asText())
            "dataStorage"->desktopPartsService.deleteDataStorage(request["name"].asText())
            "graphicsCard"->desktopPartsService.deleteGraphicsCard(request["name"].asText())
            "memory"->desktopPartsService.deleteMemory(request["name"].asText())
            "motherBoard"->desktopPartsService.deleteMotherBoard(request["name"].asText())
            "powerSupply"->desktopPartsService.deletePowerSupply(request["name"].asText())
        }
        return ResponseEntity.ok("Deleted")
    }
}