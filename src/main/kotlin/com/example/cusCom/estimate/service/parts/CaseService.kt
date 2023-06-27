package com.example.cusCom.estimate.service.parts

import com.example.cusCom.estimate.dto.parts.Case
import com.example.cusCom.estimate.repository.parts.CaseRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CaseService(private val caseRepo:CaseRepository) {

    @Transactional(readOnly = true)
    fun getCaseList(): List<Case> {
        val caseList:List<Case> = caseRepo.findAll().map{
                entity-> Case(entity.name,
                entity.manufacturer,
                entity.caseType,
                entity.maxMotherBoard,
                entity.maxCoolingFan,
                entity.builtInCoolingFan,
                entity.height,
                entity.length,
                entity.width,
                entity.powerLength,
                entity.cpuCoolerHeight,
                entity.graphicsCardLength)
        }
        for(e: Case in caseList)
            println("this case is ${e.name}")
        return caseList
    }
}