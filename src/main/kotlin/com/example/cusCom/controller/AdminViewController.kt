package com.example.cusCom.controller

import com.example.cusCom.config.PagePathConfig
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/CusCom/admin")
class AdminViewController(private val pagePathConfig: PagePathConfig) {

    @GetMapping("/main")
    fun mainPage():String{
        return pagePathConfig.admin.main
    }

    @GetMapping("/editCase")
    fun editCaseData():String{
        val caseEditPath=pagePathConfig.admin.edit[PagePathConfig.PathConfig.AdminPathKey.CASE]
        return caseEditPath?:pagePathConfig.redirect
    }

    @GetMapping("/editCPU")
    fun editCPUData():String{
        val cpuEditPath=pagePathConfig.admin.edit[PagePathConfig.PathConfig.AdminPathKey.CPU]
        return cpuEditPath?:pagePathConfig.redirect
    }

    @GetMapping("/editCPUCooler")
    fun editCPUCoolerData():String{
        val cpuCoolerEditPath=pagePathConfig.admin.edit[PagePathConfig.PathConfig.AdminPathKey.CPUCOOLER]
        return cpuCoolerEditPath?:pagePathConfig.redirect
    }

    @GetMapping("/editDataStorage")
    fun editDataStorageData():String{
        val dataStorageEditPath=pagePathConfig.admin.edit[PagePathConfig.PathConfig.AdminPathKey.DATASTORAGE]
        return dataStorageEditPath?:pagePathConfig.redirect
    }

    @GetMapping("/editGraphicsCard")
    fun editGraphicsCardData():String{
        val graphicsCardEditPath=pagePathConfig.admin.edit[PagePathConfig.PathConfig.AdminPathKey.GRAPHICSCARD]
        return graphicsCardEditPath?:pagePathConfig.redirect
    }

    @GetMapping("/editMemory")
    fun editMemoryData():String{
        val memoryEditPath=pagePathConfig.admin.edit[PagePathConfig.PathConfig.AdminPathKey.MEMORY]
        return memoryEditPath?:pagePathConfig.redirect
    }

    @GetMapping("/editMotherBoard")
    fun editMotherBoardData():String{
        val motherBoardEditPath=pagePathConfig.admin.edit[PagePathConfig.PathConfig.AdminPathKey.MOTHERBOARD]
        return motherBoardEditPath?:pagePathConfig.redirect
    }

    @GetMapping("/editPowerSupply")
    fun editPowerSupplyData():String{
        val powerSupplyEditPath=pagePathConfig.admin.edit[PagePathConfig.PathConfig.AdminPathKey.POWERSUPPLY]
        return powerSupplyEditPath?:pagePathConfig.redirect
    }
}