package com.example.cusCom.controller

import com.example.cusCom.provideContent.service.DesktopPartsService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/CusCom/adminPage")
class AdminViewController(private val desktopPartsService: DesktopPartsService) {

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
        return "partsListAdmin"
    }

    @GetMapping("/editCase")
    fun editCaseData(@RequestParam("Case") data:String, model:Model):String{
        model.addAttribute("item", desktopPartsService.findCase(data))
        model.addAttribute("firstCreate",data=="Sample")
        return "editPages/editCase"
    }

    @GetMapping("/editCPU")
    fun editCPUData(@RequestParam("CPU") data:String, model:Model):String{
        model.addAttribute("item", desktopPartsService.findCpu(data))
        model.addAttribute("firstCreate",data=="Sample")
        return "editPages/editCPU"
    }

    @GetMapping("/editCPUCooler")
    fun editCPUCoolerData(@RequestParam("CPUCooler") data:String, model:Model):String{
        model.addAttribute("item", desktopPartsService.findCpuCooler(data))
        model.addAttribute("firstCreate",data=="Sample")
        return "editPages/editCPUCooler"
    }

    @GetMapping("/editDataStorage")
    fun editDataStorageData(@RequestParam("DataStorage") data:String, model:Model):String{
        model.addAttribute("item", desktopPartsService.findDataStorage(data))
        model.addAttribute("firstCreate",data=="Sample")
        return "editPages/editDataStorage"
    }

    @GetMapping("/editGraphicsCard")
    fun editGraphicsCardData(@RequestParam("GraphicsCard") data:String, model:Model):String{
        model.addAttribute("item", desktopPartsService.findGraphicsCard(data))
        model.addAttribute("firstCreate",data=="Sample")
        return "editPages/editGraphicsCard"
    }

    @GetMapping("/editMemory")
    fun editMemoryData(@RequestParam("Memory") data:String, model:Model):String{
        model.addAttribute("item", desktopPartsService.findMemory(data))
        model.addAttribute("firstCreate",data=="Sample")
        return "editPages/editMemory"
    }

    @GetMapping("/editMotherBoard")
    fun editMotherBoardData(@RequestParam("MotherBoard") data:String, model:Model):String{
        model.addAttribute("item", desktopPartsService.findMotherBoard(data))
        model.addAttribute("firstCreate",data=="Sample")
        return "editPages/editMotherBoard"
    }

    @GetMapping("/editPowerSupply")
    fun editPowerSupplyData(@RequestParam("PowerSupply") data:String, model:Model):String{
        model.addAttribute("item", desktopPartsService.findPowerSupply(data))
        model.addAttribute("firstCreate",data=="Sample")
        return "editPages/editPowerSupply"
    }
}