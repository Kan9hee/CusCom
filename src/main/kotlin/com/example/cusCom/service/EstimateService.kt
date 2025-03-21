package com.example.cusCom.service

import com.example.cusCom.config.DBStringConfig
import com.example.cusCom.config.InnerStringsConfig
import com.example.cusCom.exception.CusComErrorCode
import com.example.cusCom.exception.CusComException
import com.example.cusCom.dto.EstimateDTO
import com.example.cusCom.entity.mongoDB.EstimateEntity
import com.example.cusCom.entity.mySQL.MotherBoardFormFactor
import com.fasterxml.jackson.databind.ObjectMapper
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EstimateService(private val mongoTemplate: MongoTemplate,
                      private val desktopPartsService: DesktopPartsService,
                      private val innerStringsConfig: InnerStringsConfig,
                      private val dbStringConfig: DBStringConfig) {

    @Transactional
    fun saveUserEstimate(estimateDTO: EstimateDTO){
        mongoTemplate.insert(
            EstimateEntity(
            ObjectId(),
            estimateDTO.userName,
            estimateDTO.posted,
            estimateDTO.cpu,
            estimateDTO.motherBoard,
            estimateDTO.memory,
            estimateDTO.dataStorage,
            estimateDTO.graphicsCard,
            estimateDTO.cpuCooler,
            estimateDTO.powerSupply,
            estimateDTO.desktopCase)
        )
    }

    @Transactional
    fun updateUserEstimate(id:ObjectId, updatedEstimateDTO:EstimateDTO){
        val query = Query(Criteria.where(dbStringConfig.mongodb.id).`is`(id))
        val update = Update()
        var updatedFields = ObjectMapper().convertValue(updatedEstimateDTO, Map::class.java)
        updatedFields -= dbStringConfig.mongodb.id

        for (field in updatedFields.keys)
            update.set(field.toString(), updatedFields[field])

        mongoTemplate.updateFirst(query, update, EstimateEntity::class.java)
    }

    @Transactional
    fun getUserEstimateById(id:String): EstimateDTO {
        val entity = mongoTemplate.findById(ObjectId(id), EstimateEntity::class.java)
            ?: throw CusComException(CusComErrorCode.NotFoundEstimate)
        return EstimateDTO(
            entity._id.toHexString(),
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
    fun getUserEstimateList(option:String,name:String): List<EstimateDTO> {
        val query= Query(Criteria.where(option).`is`(name))
        val estimateDTOLists:List<EstimateDTO> = mongoTemplate.find(query,EstimateEntity::class.java).map {
            entity: EstimateEntity ->
                EstimateDTO(entity._id.toHexString(),
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
        return estimateDTOLists
    }

    @Transactional
    fun deleteUserEstimate(option:String,value:String){
        val query=Query(Criteria.where(option).`is`(if(option==dbStringConfig.mongodb.id) ObjectId(value) else value))
        val result=mongoTemplate.remove(query, EstimateEntity::class.java)

        if(result.deletedCount==dbStringConfig.mongodb.deleteFailValue)
            throw CusComException(CusComErrorCode.FailedDeleteEstimate)
    }

    @Transactional
    fun analyzeEstimate(estimateDTO: EstimateDTO?): HashMap<String, Int> {
        if(estimateDTO==null)
            throw CusComException(CusComErrorCode.NotFoundEstimate)

        val analyzeMap=HashMap<String,Int>()

        val cpuCooler=desktopPartsService.findCpuCooler(estimateDTO.cpuCooler)
        val case=desktopPartsService.findCase(estimateDTO.desktopCase)
        val graphicsCard=desktopPartsService.findGraphicsCard(estimateDTO.graphicsCard)
        val memory=desktopPartsService.findMemory(estimateDTO.memory)
        val powerSupply=desktopPartsService.findPowerSupply(estimateDTO.powerSupply)
        val cpu=desktopPartsService.findCpu(estimateDTO.cpu)

        val needMoreCoolerHeight =
            if((memory.height-innerStringsConfig.parts.memoryInterval)<0) 0
            else memory.height-innerStringsConfig.parts.memoryInterval

        analyzeMap["powerSupplyOutput"]=powerSupply.power
        analyzeMap["totalTDP"]=cpu.tdp+cpuCooler.tdp+graphicsCard.maxPower

        analyzeMap["caseCoolerHeight"]=case.cpuCoolerHeight
        analyzeMap["coolerHeight"]=cpuCooler.height + needMoreCoolerHeight
        analyzeMap["caseGraphicLength"]=case.graphicsCardLength
        analyzeMap["graphicsCardLength"]=graphicsCard.length
        analyzeMap["casePowerSupplySize"]=case.powerLength
        analyzeMap["powerSupplySize"]=powerSupply.length

        return analyzeMap
    }

    fun checkDesktopEstimate(estimateDTO: EstimateDTO){
        if(estimateDTO.run { cpu.isEmpty() ||
                    desktopCase.isEmpty() ||
                    dataStorage.isEmpty() ||
                    memory.isEmpty() ||
                    graphicsCard.isEmpty() ||
                    cpuCooler.isEmpty() ||
                    motherBoard.isEmpty() ||
                    powerSupply.isEmpty() })
            throw CusComException(CusComErrorCode.UnfinishedEstimate)

        val cpuCooler=desktopPartsService.findCpuCooler(estimateDTO.cpuCooler)
        val case=desktopPartsService.findCase(estimateDTO.desktopCase)
        val graphicsCard=desktopPartsService.findGraphicsCard(estimateDTO.graphicsCard)
        val memory=desktopPartsService.findMemory(estimateDTO.memory)
        val powerSupply=desktopPartsService.findPowerSupply(estimateDTO.powerSupply)
        val cpu=desktopPartsService.findCpu(estimateDTO.cpu)
        val motherBoard=desktopPartsService.findMotherBoard(estimateDTO.motherBoard)

        val caseMaxFormFactor= MotherBoardFormFactor.fromString(case.motherBoardFormFactor)
        val motherBoardsFormFactor= MotherBoardFormFactor.fromString(motherBoard.motherBoardFormFactor)

        if(cpuCooler.height>=case.cpuCoolerHeight)
            throw CusComException(CusComErrorCode.OversizeCooler)
        if(graphicsCard.length>=case.graphicsCardLength)
            throw CusComException(CusComErrorCode.OversizeGraphicsCard)
        if((innerStringsConfig.parts.memoryInterval-memory.height)>0)
            if(cpuCooler.height+(innerStringsConfig.parts.memoryInterval-memory.height)>=case.width)
                throw CusComException(CusComErrorCode.InterferenceMemory)
        if(motherBoardsFormFactor.length>caseMaxFormFactor.length
            ||motherBoardsFormFactor.width>caseMaxFormFactor.width)
            throw CusComException(CusComErrorCode.OversizeMotherBoard)
        if(motherBoard.socket!=cpu.socket)
            throw CusComException(CusComErrorCode.MismatchSocket)
        if(powerSupply.length>case.powerLength)
            throw CusComException(CusComErrorCode.OversizePowerSupply)
        if(memory.type!=motherBoard.memoryType)
            throw CusComException(CusComErrorCode.MismatchMemory)
        if(graphicsCard.maxPower + cpu.tdp + cpuCooler.tdp >= powerSupply.power)
            throw CusComException(CusComErrorCode.PowerSupplyShortage)
    }
}