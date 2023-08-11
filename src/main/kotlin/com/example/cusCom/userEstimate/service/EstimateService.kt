package com.example.cusCom.userEstimate.service

import com.example.cusCom.provideContent.dto.parts.Case
import com.example.cusCom.provideContent.entity.parts.CaseEntity
import com.example.cusCom.userEstimate.dto.Estimate
import com.example.cusCom.userEstimate.exception.EstimateException
import com.example.cusCom.provideContent.repository.MotherBoardFormFactorRepository
import com.example.cusCom.provideContent.service.DesktopPartsService
import com.example.cusCom.userEstimate.entity.EstimateEntity
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.transaction.annotation.Transactional
import org.springframework.stereotype.Service

@Service
class EstimateService(private val mongoTemplate: MongoTemplate,
                      private val motherBoardRepo:MotherBoardFormFactorRepository,
                      private val desktopPartsService: DesktopPartsService) {

    @Transactional
    fun saveUserEstimate(estimate: Estimate){
        mongoTemplate.insert(EstimateEntity(
            ObjectId(),
            estimate.userName,
            estimate.cpu,
            estimate.motherBoard,
            estimate.memory,
            estimate.dataStorage,
            estimate.graphicsCard,
            estimate.cpuCooler,
            estimate.powerSupply,
            estimate.case)
        )
    }

    @Transactional
    fun getUserEstimateById(id:ObjectId): Estimate {
        val entity: EstimateEntity=mongoTemplate.findById(id,EstimateEntity::class.java)!!
        return Estimate(entity._id.toHexString(),
            entity.userName,
            entity.cpu,
            entity.motherBoard,
            entity.memory,
            entity.dataStorage,
            entity.graphicsCard,
            entity.cpuCooler,
            entity.powerSupply,
            entity.case)
    }

    @Transactional
    fun getUserEstimateList(): List<Estimate> {
        val estimateList:List<Estimate> =mongoTemplate.findAll(EstimateEntity::class.java).map {
            entity:EstimateEntity->Estimate(entity._id.toHexString(),
            entity.userName,
            entity.cpu,
            entity.motherBoard,
            entity.memory,
            entity.dataStorage,
            entity.graphicsCard,
            entity.cpuCooler,
            entity.powerSupply,
            entity.case
            )
        }
        return estimateList
    }

    @Transactional
    fun deleteUserEstimate(option:String,value:String){
        val query= Query(Criteria.where(option).`is`(ObjectId(value)))
        mongoTemplate.remove(query, EstimateEntity::class.java)
    }

    fun checkDesktopEstimate(estimate: Estimate){
        val cpuCooler=desktopPartsService.findCpuCooler(estimate.cpuCooler)
        val case=desktopPartsService.findCase(estimate.case)
        val graphicsCard=desktopPartsService.findGraphicsCard(estimate.graphicsCard)
        val memory=desktopPartsService.findMemory(estimate.memory)
        val powerSupply=desktopPartsService.findPowerSupply(estimate.powerSupply)
        val cpu=desktopPartsService.findCpu(estimate.cpu)
        val motherBoard=desktopPartsService.findMotherBoard(estimate.motherBoard)

        val caseMaxFormFactor=motherBoardRepo.findById(case.maxMotherBoard).get()
        val motherBoardFormFactor=motherBoardRepo.findById(motherBoard.formFactor).get()

        if(cpuCooler.height>case.cpuCoolerHeight)
            throw EstimateException("쿨러 허용 높이 초과")
        if(graphicsCard.length>case.graphicsCardLength)
            throw EstimateException("그래픽카드 허용 길이 초과")
        if(memory.height>44)
            throw EstimateException("램 간섭 발생")
        if(motherBoardFormFactor.length>caseMaxFormFactor.length
            ||motherBoardFormFactor.width>caseMaxFormFactor.width)
            throw EstimateException("마더보드 허용 규격 초과")
        if(powerSupply.length>case.powerLength)
            throw EstimateException("파워서플라이 허용 규격 초과")

        if(memory.type!=motherBoard.memoryType)
            throw EstimateException("메모리 규격 불일치")

        if(graphicsCard.maxPower + cpu.TDP + cpuCooler.TDP > powerSupply.power)
            throw EstimateException("파워서플라이 전력 부족")
    }
}