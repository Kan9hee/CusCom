package com.example.cusCom.estimate.controller

import com.example.cusCom.estimate.dto.User
import com.example.cusCom.estimate.exception.EstimateException
import com.example.cusCom.estimate.service.DesktopPartsService
import com.example.cusCom.estimate.service.EstimateService
import com.example.cusCom.estimate.service.UserService
import com.example.cusCom.estimate.service.parts.*
import com.google.gson.Gson
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class TestController(private val userService: UserService,
                     private val desktopPartsService: DesktopPartsService,
                     private val estimateService: EstimateService) {

    @GetMapping("/CusCom/login")
    fun loginPage():String{
        return "loginPage"
    }

    @GetMapping("/CusCom/join")
    fun getUserJoin():String{
        return "joinPage"
    }

    @PostMapping("/CusCom/join")
    fun postUserJoin(@RequestParam("user") userJSON:String):String{
        userService.joinUser(Gson().fromJson(userJSON, User::class.java))
        return "redirect:/CusCom/login"
    }

    @PostMapping("/main")
    fun logout():String{
        return "/CusCom/login"
    }

    @GetMapping("/main")
    fun getWelcome():String{
        return "mainPage"
    }

    @GetMapping("/estimate")
    fun getDataTest(model:Model):String{
        model.addAttribute("userName",SecurityContextHolder.getContext().authentication.name)
        model.addAttribute("caseList",desktopPartsService.getCaseList())
        model.addAttribute("cpuCoolerList",desktopPartsService.getCpuCoolerList())
        model.addAttribute("cpuList",desktopPartsService.getCPUList())
        model.addAttribute("dataStorageList",desktopPartsService.getDataStorageList())
        model.addAttribute("graphicsCardList",desktopPartsService.getGraphicsCardList())
        model.addAttribute("memoryList",desktopPartsService.getMemoryList())
        model.addAttribute("motherBoardList",desktopPartsService.getMotherBoardList())
        model.addAttribute("powerSupplyList",desktopPartsService.getPowerSupplyList())
        return "customPage"
    }

    @PostMapping("/estimate")
    fun postDataTest(@RequestParam("Estimates") estimateJSON:String):String{
        println("estimate is $estimateJSON")
        return "redirect:/main"
    }

    @GetMapping("/clear")
    fun testClearPage(){}
    @GetMapping("/fail")
    fun testFailPage(){}

    @ExceptionHandler(EstimateException::class)
    fun estimateException(ex:EstimateException,model:Model):String{
        model.addAttribute("errorMsg",ex.message)
        return "failView"
    }
}