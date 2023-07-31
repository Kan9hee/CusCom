package com.example.cusCom.controller

import com.example.cusCom.provideContent.dto.User
import com.example.cusCom.userEstimate.exception.EstimateException
import com.example.cusCom.provideContent.service.DesktopPartsService
import com.example.cusCom.provideContent.service.UserService
import com.google.gson.Gson
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*


@Controller
class TestController(private val userService: UserService,
                     private val desktopPartsService: DesktopPartsService) {

    @GetMapping("/CusCom/login")
    fun loginPage():String{
        return "loginPage"
    }

    @GetMapping("/CusCom/joinPage")
    fun getUserJoin():String{
        return "joinPage"
    }

    @PostMapping("/CusCom/join")
    fun postUserJoin(@RequestParam("user") userJSON:String): ResponseEntity<String> {
        userService.joinUser(Gson().fromJson(userJSON, User::class.java))
        return ResponseEntity.ok("Success")
    }

    @PostMapping("/main")
    fun logout():String{
        return "/CusCom/loginPage"
    }

    @GetMapping("/main")
    fun getWelcome():String{
        return "mainPage"
    }

    @GetMapping("/customPage")
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

    @GetMapping("/clear")
    fun testClearPage(){}
    @GetMapping("/fail")
    fun testFailPage(){}

    @ExceptionHandler(EstimateException::class)
    fun estimateException(ex: EstimateException, model:Model):String{
        model.addAttribute("errorMsg",ex.message)
        return "failView"
    }
}