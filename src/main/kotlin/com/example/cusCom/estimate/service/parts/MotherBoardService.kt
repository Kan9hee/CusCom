package com.example.cusCom.estimate.service.parts

import com.example.cusCom.estimate.dto.parts.MotherBoard
import com.example.cusCom.estimate.repository.parts.MotherBoardRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MotherBoardService(private val motherBoardRepo:MotherBoardRepository) {

    @Transactional(readOnly = true)
    fun getMotherBoardList(): List<MotherBoard> {
        val motherBoardList:List<MotherBoard> = motherBoardRepo.findAll().map{
                entity-> MotherBoard(entity.name,
            entity.manufacturer,
            entity.cpuType,
            entity.socket,
            entity.chipset,
            entity.formFactor,
            entity.memoryType,
            entity.memorySlot,
            entity.ssdM2Slot,
            entity.ssdSATASlot)
        }
        for(e: MotherBoard in motherBoardList)
            println("this MotherBoard is ${e.name}")
        return motherBoardList
    }
}