package com.example.cusCom.sharePlace.service

import com.example.cusCom.estimate.dto.Estimate
import com.example.cusCom.estimate.entity.MotherBoardFormFactorEntity
import com.example.cusCom.estimate.exception.EstimateException
import com.example.cusCom.estimate.repository.MotherBoardFormFactorRepository
import com.example.cusCom.sharePlace.entity.SharePlaceEntity
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.transaction.annotation.Transactional
import org.springframework.stereotype.Service

@Service
class SharePlaceService(private val mongoTemplate: MongoTemplate,
                        private val motherBoardRepo:MotherBoardFormFactorRepository) {

    @Transactional
    fun saveUserEstimate(estimate: Estimate){
        mongoTemplate.insert(estimate.toEstimateEntity(),"user-estimates")
    }

    @Transactional
    fun getUserEstimateList(): MutableList<SharePlaceEntity> {
        return mongoTemplate.findAll(SharePlaceEntity::class.java,"user-estimates")
    }

    @Transactional(readOnly=true)
    private fun getMotherboardSize(formFactor:String): MotherBoardFormFactorEntity {
        return motherBoardRepo.findById(formFactor).get()
    }

    fun checkDesktopEstimate(estimate: Estimate){
        val caseMaxFormFactor=getMotherboardSize(estimate.case.maxMotherBoard)
        val motherBoardFormFactor=getMotherboardSize(estimate.motherBoard.formFactor)

        if(estimate.cpuCooler.height>estimate.case.cpuCoolerHeight)
            throw EstimateException("쿨러 허용 높이 초과")
        if(estimate.graphicsCard.length>estimate.case.graphicsCardLength)
            throw EstimateException("그래픽카드 허용 길이 초과")
        if(estimate.memory.height>44)
            throw EstimateException("램 간섭 발생")
        if(motherBoardFormFactor.length>caseMaxFormFactor.length
            ||motherBoardFormFactor.width>caseMaxFormFactor.width)
            throw EstimateException("마더보드 허용 규격 초과")
        if(estimate.powerSupply.length>estimate.case.powerLength)
            throw EstimateException("파워서플라이 허용 규격 초과")

        if(estimate.memory.type!=estimate.motherBoard.memoryType)
            throw EstimateException("메모리 규격 불일치")

        if(estimate.graphicsCard.maxPower + estimate.cpu.TDP + estimate.cpuCooler.TDP
            > estimate.powerSupply.power)
            throw EstimateException("파워서플라이 전력 부족")
    }
}