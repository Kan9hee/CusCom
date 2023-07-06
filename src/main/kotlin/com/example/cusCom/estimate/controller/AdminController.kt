package com.example.cusCom.estimate.controller

import com.example.cusCom.estimate.dto.parts.CPU
import com.example.cusCom.estimate.service.DesktopPartsService
import com.google.gson.Gson
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

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

    @GetMapping("/editCPU")
    fun dataTest():String{
        return "dataInputPage"
    }

    @PostMapping("/editCPU")
    fun postDataTest(@RequestParam("CPU") cpuJSON:String):String{
        desktopPartsService.createCPU(Gson().fromJson(cpuJSON, CPU::class.java))
        return "redirect:/main"
    }
}