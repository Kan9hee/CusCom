package com.example.cusCom.estimate.service.parts

import com.example.cusCom.estimate.dto.parts.Memory
import com.example.cusCom.estimate.repository.parts.MemoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemoryService(private val memoryRepo:MemoryRepository) {

    @Transactional(readOnly = true)
    fun getMemoryList(): List<Memory> {
        val memoryList:List<Memory> = memoryRepo.findAll().map{
                entity-> Memory(entity.name,
            entity.manufacturer,
            entity.type,
            entity.capacity,
            entity.height)
        }
        for(e: Memory in memoryList)
            println("this Memory is ${e.name}")
        return memoryList
    }
}