package com.example.cusCom.estimate.controller

import com.example.cusCom.estimate.dto.Estimate
import com.example.cusCom.estimate.exception.EstimateException
import com.example.cusCom.estimate.service.DesktopPartsService
import com.example.cusCom.estimate.service.EstimateService
import com.example.cusCom.estimate.service.parts.*
import com.google.gson.Gson
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(private val desktopPartsService: DesktopPartsService,
                     private val estimateService: EstimateService) {

    @GetMapping("/test")
    fun getDataTest(){
        desktopPartsService.getCaseList()
        desktopPartsService.getCpuCoolerList()
        desktopPartsService.getCPUList()
        desktopPartsService.getDataStorageList()
        desktopPartsService.getGraphicsCardList()
        desktopPartsService.getMemoryList()
        desktopPartsService.getMotherBoardList()
        desktopPartsService.getPowerSupplyList()
    }

    @PostMapping("/postTest")
    fun postDataTest(@RequestParam("estimate") estimateJSON:String):String{
        val tempEstimates = Gson().fromJson(estimateJSON,Estimate::class.java)
        estimateService.checkDesktopEstimate(tempEstimates)
        return "clear"
    }

    @GetMapping("clear")
    fun testClearPage(){}
    @GetMapping("fail")
    fun testFailPage(){}

    @ExceptionHandler(EstimateException::class)
    fun estimateException(ex:EstimateException,model:Model):String{
        model.addAttribute("errorMsg",ex.message)
        return "fail"
    }
}