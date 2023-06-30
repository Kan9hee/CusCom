package com.example.cusCom.estimate.service

import com.example.cusCom.estimate.dto.Estimate
import com.example.cusCom.estimate.dto.parts.*
import com.example.cusCom.estimate.entity.MotherBoardFormFactorEntity
import com.example.cusCom.estimate.exception.EstimateException
import com.example.cusCom.estimate.repository.EstimateRepository
import com.example.cusCom.estimate.repository.MotherBoardFormFactorRepository
import org.springframework.transaction.annotation.Transactional
import org.springframework.stereotype.Service

@Service
class EstimateService(private val estimateRepo:EstimateRepository,
                      private val motherBoardRepo:MotherBoardFormFactorRepository) {

    @Transactional(readOnly=true)
    private fun getMotherboardSize(formFactor:String): MotherBoardFormFactorEntity {
        return motherBoardRepo.findById(formFactor).get()
    }

    @Transactional
    fun saveUserEstimate(information:Estimate){
        estimateRepo.save(information.toEstimateEntity())
    }

    fun checkDesktopSpace(case: Case,
                          cpuCooler: CPUCooler,
                          graphicsCard: GraphicsCard,
                          memory: Memory,
                          motherBoard: MotherBoard,
                          powerSupply: PowerSupply){

        val caseMaxFormFactor=getMotherboardSize(case.maxMotherBoard)
        val motherBoardFormFactor=getMotherboardSize(motherBoard.formFactor)

        if(cpuCooler.height>case.cpuCoolerHeight)
            throw EstimateException("쿨러 허용 높이 초과: ${cpuCooler.height-case.cpuCoolerHeight}mm")
        if(graphicsCard.length>case.graphicsCardLength)
            throw EstimateException("그래픽카드 허용 길이 초과: ${graphicsCard.length-case.graphicsCardLength}mm")
        if(memory.height>44)
            throw EstimateException("램 간섭 발생")
        if(motherBoardFormFactor.length>caseMaxFormFactor.length
            ||motherBoardFormFactor.width>caseMaxFormFactor.width)
            throw EstimateException("마더보드 허용 규격 초과")
        if(powerSupply.length>case.powerLength)
            throw EstimateException("파워서플라이 허용 규격 초과")
    }
}