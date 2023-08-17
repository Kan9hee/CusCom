package com.example.cusCom.provideContent.service

import com.example.cusCom.exception.EstimateErrorCode
import com.example.cusCom.exception.EstimateException
import com.example.cusCom.provideContent.dto.Estimate
import com.example.cusCom.provideContent.repository.MotherBoardFormFactorRepository
import com.example.cusCom.provideContent.entity.mongoDB.EstimateEntity
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
        mongoTemplate.insert(
            EstimateEntity(
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
        val entity: EstimateEntity =mongoTemplate.findById(id, EstimateEntity::class.java)!!
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
            entity: EstimateEntity ->
            Estimate(entity._id.toHexString(),
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

    fun checkEstimateEmptyElement(estimate: Estimate): Boolean {
        return estimate.cpu.isNotEmpty() &&
                estimate.case.isNotEmpty() &&
                estimate.dataStorage.isNotEmpty() &&
                estimate.memory.isNotEmpty() &&
                estimate.graphicsCard.isNotEmpty() &&
                estimate.cpuCooler.isNotEmpty() &&
                estimate.motherBoard.isNotEmpty() &&
                estimate.powerSupply.isNotEmpty()
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
            throw EstimateException(EstimateErrorCode.OversizeCooler)
        if(graphicsCard.length>case.graphicsCardLength)
            throw EstimateException(EstimateErrorCode.OversizeGraphicsCard)
        if(memory.height>44)
            throw EstimateException(EstimateErrorCode.InterferenceMemory)
        if(motherBoardFormFactor.length>caseMaxFormFactor.length
            ||motherBoardFormFactor.width>caseMaxFormFactor.width)
            throw EstimateException(EstimateErrorCode.OversizeMotherBoard)
        if(powerSupply.length>case.powerLength)
            throw EstimateException(EstimateErrorCode.OversizePowerSupply)

        if(memory.type!=motherBoard.memoryType)
            throw EstimateException(EstimateErrorCode.MismatchMemory)

        if(graphicsCard.maxPower + cpu.TDP + cpuCooler.TDP > powerSupply.power)
            throw EstimateException(EstimateErrorCode.PowerSupplyShortage)
    }
}