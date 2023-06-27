package com.example.cusCom.estimate.service.parts

import com.example.cusCom.estimate.dto.parts.CPUCooler
import com.example.cusCom.estimate.repository.parts.CPUCoolerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CPUCoolerService(private val cpuCoolerRepo:CPUCoolerRepository) {

    @Transactional(readOnly = true)
    fun getCpuCoolerList(): List<CPUCooler> {
        val cpuCoolerList:List<CPUCooler> = cpuCoolerRepo.findAll().map{
                entity->
            CPUCooler(entity.name,
            entity.manufacturer,
            entity.coolingType,
            entity.coolerForm,
            entity.height,
            entity.length,
            entity.width)
        }
        for(e: CPUCooler in cpuCoolerList)
            println("this cpuCooler is ${e.name}")
        return cpuCoolerList
    }
}