package com.example.cusCom.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/CusCom/adminPage")
class AdminViewController() {

    @GetMapping("/main")
    fun mainPage():String{
        return "partsListAdmin"
    }

    @GetMapping("/editCase")
    fun editCaseData():String{
        return "editPages/editCase"
    }

    @GetMapping("/editCPU")
    fun editCPUData():String{
        return "editPages/editCPU"
    }

    @GetMapping("/editCPUCooler")
    fun editCPUCoolerData():String{
        return "editPages/editCPUCooler"
    }

    @GetMapping("/editDataStorage")
    fun editDataStorageData():String{
        return "editPages/editDataStorage"
    }

    @GetMapping("/editGraphicsCard")
    fun editGraphicsCardData():String{
        return "editPages/editGraphicsCard"
    }

    @GetMapping("/editMemory")
    fun editMemoryData():String{
        return "editPages/editMemory"
    }

    @GetMapping("/editMotherBoard")
    fun editMotherBoardData():String{
        return "editPages/editMotherBoard"
    }

    @GetMapping("/editPowerSupply")
    fun editPowerSupplyData():String{
        return "editPages/editPowerSupply"
    }
}