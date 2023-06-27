package com.example.cusCom.estimate.service.parts

import com.example.cusCom.estimate.dto.parts.CPU
import com.example.cusCom.estimate.repository.parts.CPURepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CPUService(private val cpuRepo:CPURepository) {

    @Transactional(readOnly = true)
    fun getCPUList(): List<CPU> {
        val cpuList:List<CPU> = cpuRepo.findAll().map{
                entity->CPU(entity.name,
            entity.manufacturer,
            entity.socket,
            entity.memoryType,
            entity.core,
            entity.thread,
            entity.isBuiltInGraphics,
            entity.builtInGraphicName,
            entity.TDP)
        }
        for(e: CPU in cpuList)
            println("this CPU is ${e.name}")
        return cpuList
    }
}