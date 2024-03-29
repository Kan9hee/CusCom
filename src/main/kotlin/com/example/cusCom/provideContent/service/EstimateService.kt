package com.example.cusCom.provideContent.service

import com.example.cusCom.config.DBStringConfig
import com.example.cusCom.config.InnerStringsConfig
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
                      private val desktopPartsService: DesktopPartsService,
                      private val innerStringsConfig: InnerStringsConfig,
                      private val dbStringConfig: DBStringConfig) {

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
        val query = Query(Criteria.where(dbStringConfig.mongodb.id).`is`(id))
        val update = Update()
        var updatedFields = ObjectMapper().convertValue(updatedEstimate, Map::class.java)
        updatedFields -= dbStringConfig.mongodb.id

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
        val query=Query(Criteria.where(option).`is`(if(option==dbStringConfig.mongodb.id) ObjectId(value) else value))
        val result=mongoTemplate.remove(query, EstimateEntity::class.java)

        if(result.deletedCount==dbStringConfig.mongodb.deleteFailValue)
            throw CusComException(CusComErrorCode.FailedDeleteEstimate)
    }

    @Transactional
    fun analyzeEstimate(estimate: Estimate?): HashMap<String, Int> {
        if(estimate==null)
            throw CusComException(CusComErrorCode.NotFoundEstimate)

        val analyzeMap=HashMap<String,Int>()

        val cpuCooler=desktopPartsService.findCpuCooler(innerStringsConfig.parts.name,estimate.cpuCooler)
        val case=desktopPartsService.findCase(innerStringsConfig.parts.name,estimate.desktopCase)
        val graphicsCard=desktopPartsService.findGraphicsCard(innerStringsConfig.parts.name,estimate.graphicsCard)
        val memory=desktopPartsService.findMemory(innerStringsConfig.parts.name,estimate.memory)
        val powerSupply=desktopPartsService.findPowerSupply(innerStringsConfig.parts.name,estimate.powerSupply)
        val cpu=desktopPartsService.findCpu(innerStringsConfig.parts.name,estimate.cpu)

        val needMoreCoolerHeight =
            if((memory.height-innerStringsConfig.parts.memoryInterval)<0) 0
            else memory.height-innerStringsConfig.parts.memoryInterval

        analyzeMap["powerSupplyOutput"]=powerSupply.power
        analyzeMap["totalTDP"]=cpu.TDP+cpuCooler.TDP+graphicsCard.maxPower

        analyzeMap["caseCoolerHeight"]=case.cpuCoolerHeight
        analyzeMap["coolerHeight"]=cpuCooler.height + needMoreCoolerHeight
        analyzeMap["caseGraphicLength"]=case.graphicsCardLength
        analyzeMap["graphicsCardLength"]=graphicsCard.length
        analyzeMap["casePowerSupplySize"]=case.powerLength
        analyzeMap["powerSupplySize"]=powerSupply.length

        return analyzeMap
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
            throw CusComException(CusComErrorCode.UnfinishedEstimate)

        val cpuCooler=desktopPartsService.findCpuCooler(innerStringsConfig.parts.name,estimate.cpuCooler)
        val case=desktopPartsService.findCase(innerStringsConfig.parts.name,estimate.desktopCase)
        val graphicsCard=desktopPartsService.findGraphicsCard(innerStringsConfig.parts.name,estimate.graphicsCard)
        val memory=desktopPartsService.findMemory(innerStringsConfig.parts.name,estimate.memory)
        val powerSupply=desktopPartsService.findPowerSupply(innerStringsConfig.parts.name,estimate.powerSupply)
        val cpu=desktopPartsService.findCpu(innerStringsConfig.parts.name,estimate.cpu)
        val motherBoard=desktopPartsService.findMotherBoard(innerStringsConfig.parts.name,estimate.motherBoard)

        val caseMaxFormFactor=motherBoardRepo.findById(case.motherBoardFormFactor.name).get()

        if(cpuCooler.height>=case.cpuCoolerHeight)
            throw CusComException(CusComErrorCode.OversizeCooler)
        if(graphicsCard.length>=case.graphicsCardLength)
            throw CusComException(CusComErrorCode.OversizeGraphicsCard)
        if((innerStringsConfig.parts.memoryInterval-memory.height)>0)
            if(cpuCooler.height+(innerStringsConfig.parts.memoryInterval-memory.height)>=case.width)
                throw CusComException(CusComErrorCode.InterferenceMemory)
        if(motherBoard.motherBoardFormFactor.length>caseMaxFormFactor.length
            ||motherBoard.motherBoardFormFactor.width>caseMaxFormFactor.width)
            throw CusComException(CusComErrorCode.OversizeMotherBoard)
        if(motherBoard.socket!=cpu.socket)
            throw CusComException(CusComErrorCode.MismatchSocket)
        if(powerSupply.length>case.powerLength)
            throw CusComException(CusComErrorCode.OversizePowerSupply)
        if(memory.type!=motherBoard.memoryType)
            throw CusComException(CusComErrorCode.MismatchMemory)
        if(graphicsCard.maxPower + cpu.TDP + cpuCooler.TDP >= powerSupply.power)
            throw CusComException(CusComErrorCode.PowerSupplyShortage)
    }
}