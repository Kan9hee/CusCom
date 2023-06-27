package com.example.cusCom.estimate.controller

import com.example.cusCom.estimate.service.EstimateService
import com.example.cusCom.estimate.service.parts.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(private val caseService:CaseService,
                     private val cpuCoolerService:CPUCoolerService,
                     private val cpuService: CPUService,
                     private val dataStorageService: DataStorageService,
                     private val graphicsCardService: GraphicsCardService,
                     private val memoryService: MemoryService,
                     private val motherBoardService: MotherBoardService,
                     private val powerSupplyService: PowerSupplyService) {

    @GetMapping("/test")
    fun test(){
        caseService.getCaseList()
        cpuCoolerService.getCpuCoolerList()
        cpuService.getCPUList()
        dataStorageService.getDataStorageList()
        graphicsCardService.getGraphicsCardList()
        memoryService.getMemoryList()
        motherBoardService.getMotherBoardList()
        powerSupplyService.getPowerSupplyList()
    }
}