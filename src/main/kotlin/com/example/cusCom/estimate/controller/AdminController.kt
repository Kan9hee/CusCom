package com.example.cusCom.estimate.controller

import com.example.cusCom.estimate.dto.parts.*
import com.example.cusCom.estimate.service.DesktopPartsService
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import org.springframework.web.servlet.view.RedirectView
import org.springframework.web.util.UriComponentsBuilder

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

    @PostMapping("/editParts")
    fun editParts(@RequestParam("Type") type:String,
                  @RequestParam("Data") requestJSON:String): RedirectView {
        when(type){
            "case"->desktopPartsService.createCase(Gson().fromJson(requestJSON, Case::class.java))
            "cpu"->desktopPartsService.createCPU(Gson().fromJson(requestJSON, CPU::class.java))
            "cpuCooler"->desktopPartsService.createCPUCooler(Gson().fromJson(requestJSON, CPUCooler::class.java))
            "dataStorage"->desktopPartsService.createDataStorage(Gson().fromJson(requestJSON, DataStorage::class.java))
            "graphicsCard"->desktopPartsService.createGraphicsCard(Gson().fromJson(requestJSON, GraphicsCard::class.java))
            "memory"->desktopPartsService.createMemory(Gson().fromJson(requestJSON, Memory::class.java))
            "motherBoard"->desktopPartsService.createMotherBoard(Gson().fromJson(requestJSON, MotherBoard::class.java))
            "powerSupply"->desktopPartsService.createPowerSupply(Gson().fromJson(requestJSON, PowerSupply::class.java))
        }
        return RedirectView("/adminPage/main")
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