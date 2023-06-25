package com.example.cusCom.estimate.controller

import com.example.cusCom.estimate.service.EstimateService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(private val estimateService:EstimateService) {

    @GetMapping("/test")
    fun test(){
        estimateService.getMotherboardSize("ATX")
    }
}