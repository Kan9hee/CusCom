package com.example.cusCom.controller

import com.example.cusCom.provideContent.dto.parts.*
import com.example.cusCom.provideContent.service.DesktopPartsService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/adminPage")
class AdminViewController(private val desktopPartsService: DesktopPartsService) {

    @GetMapping("/admin")
    fun testAdminPage():String{
        return "fragments2/partsListAdmin"
    }

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
}