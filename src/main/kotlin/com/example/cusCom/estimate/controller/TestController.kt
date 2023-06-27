package com.example.cusCom.estimate.controller

import com.example.cusCom.estimate.service.EstimateService
import com.example.cusCom.estimate.service.parts.PowerSupplyService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(private val powerSupplyService:PowerSupplyService) {

    @GetMapping("/test")
    fun test(){
        powerSupplyService.getPowerSupplyList()
    }
}