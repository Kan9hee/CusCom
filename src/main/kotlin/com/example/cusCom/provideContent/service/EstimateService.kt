package com.example.cusCom.provideContent.service

import com.example.cusCom.exception.CusComErrorCode
import com.example.cusCom.exception.CusComException
import com.example.cusCom.provideContent.dto.Estimate
import com.example.cusCom.provideContent.entity.mongoDB.EstimateEntity
import com.example.cusCom.provideContent.repository.MotherBoardFormFactorRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.core.update
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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
            estimate.posted,
            estimate.cpu,
            estimate.motherBoard,
            estimate.memory,
            estimate.dataStorage,
            estimate.graphicsCard,
            estimate.cpuCooler,
            estimate.powerSupply,
            estimate.desktopCase)
        )
    }

    @Transactional
    fun updateUserEstimate(id:ObjectId,updatedEstimate:Estimate){
        val query = Query(Criteria.where("_id").`is`(id))
        val update = Update()
        var updatedFields = ObjectMapper().convertValue(updatedEstimate, Map::class.java)
        updatedFields -= "_id"

        for (field in updatedFields.keys)
            update.set(field.toString(), updatedFields[field])

        mongoTemplate.updateFirst(query, update, EstimateEntity::class.java)
    }

    @Transactional
    fun getUserEstimateById(id:String): Estimate {
        val entity: EstimateEntity =mongoTemplate.findById(ObjectId(id), EstimateEntity::class.java)!!
        return Estimate(entity._id.toHexString(),
            entity.userName,
            entity.posted,
            entity.cpu,
            entity.motherBoard,
            entity.memory,
            entity.dataStorage,
            entity.graphicsCard,
            entity.cpuCooler,
            entity.powerSupply,
            entity.desktopCase)
    }

    @Transactional
    fun getUserEstimateList(option:String,name:String): List<Estimate> {
        val query= Query(Criteria.where(option).`is`(name))
        val estimateList:List<Estimate> = mongoTemplate.find(query,EstimateEntity::class.java).map {
            entity: EstimateEntity ->
                Estimate(entity._id.toHexString(),
                    entity.userName,
                    entity.posted,
                    entity.cpu,
                    entity.motherBoard,
                    entity.memory,
                    entity.dataStorage,
                    entity.graphicsCard,
                    entity.cpuCooler,
                    entity.powerSupply,
                    entity.desktopCase
                )
        }
        return estimateList
    }

    @Transactional
    fun deleteUserEstimate(option:String,value:String){
        val query= Query(Criteria.where(option).`is`(if(option=="_id") ObjectId(value) else value))
        mongoTemplate.remove(query, EstimateEntity::class.java)
    }

    fun checkDesktopEstimate(estimate: Estimate){
        if(estimate.run { cpu.isEmpty() ||
                    desktopCase.isEmpty() ||
                    dataStorage.isEmpty() ||
                    memory.isEmpty() ||
                    graphicsCard.isEmpty() ||
                    cpuCooler.isEmpty() ||
                    motherBoard.isEmpty() ||
                    powerSupply.isEmpty() })
            throw CusComException(CusComErrorCode.UnfinishedCusCom)

        val cpuCooler=desktopPartsService.findCpuCooler("name",estimate.cpuCooler)
        val case=desktopPartsService.findCase("name",estimate.desktopCase)
        val graphicsCard=desktopPartsService.findGraphicsCard("name",estimate.graphicsCard)
        val memory=desktopPartsService.findMemory("name",estimate.memory)
        val powerSupply=desktopPartsService.findPowerSupply("name",estimate.powerSupply)
        val cpu=desktopPartsService.findCpu("name",estimate.cpu)
        val motherBoard=desktopPartsService.findMotherBoard("name",estimate.motherBoard)

        val caseMaxFormFactor=motherBoardRepo.findById(case.motherBoardFormFactor.name).get()

        if(cpuCooler.height>case.cpuCoolerHeight)
            throw CusComException(CusComErrorCode.OversizeCooler)
        if(graphicsCard.length>case.graphicsCardLength)
            throw CusComException(CusComErrorCode.OversizeGraphicsCard)
        if(memory.height>44)
            throw CusComException(CusComErrorCode.InterferenceMemory)
        if(motherBoard.motherBoardFormFactor.length>caseMaxFormFactor.length
            ||motherBoard.motherBoardFormFactor.width>caseMaxFormFactor.width)
            throw CusComException(CusComErrorCode.OversizeMotherBoard)
        if(powerSupply.length>case.powerLength)
            throw CusComException(CusComErrorCode.OversizePowerSupply)
        if(memory.type!=motherBoard.memoryType)
            throw CusComException(CusComErrorCode.MismatchMemory)
        if(graphicsCard.maxPower + cpu.TDP + cpuCooler.TDP > powerSupply.power)
            throw CusComException(CusComErrorCode.PowerSupplyShortage)
    }
}