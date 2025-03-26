package com.example.cusCom.service

import com.example.cusCom.config.DBStringConfig
import com.example.cusCom.config.InnerStringsConfig
import com.example.cusCom.dto.request.EstimateAnalyzeDTO
import com.example.cusCom.exception.CusComErrorCode
import com.example.cusCom.exception.CusComException
import com.example.cusCom.dto.response.EstimateDTO
import com.example.cusCom.entity.mongoDB.EstimateEntity
import com.example.cusCom.entity.mySQL.MotherBoardFormFactor
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EstimateService(private val mongoTemplate: MongoTemplate,
                      private val innerStringsConfig: InnerStringsConfig,
                      private val dbStringConfig: DBStringConfig) {

    @Transactional
    fun saveUserEstimate(saveEstimateDTO: EstimateAnalyzeDTO,userName:String){
        mongoTemplate.insert(
            EstimateEntity(
                ObjectId(),
                userName,
            false,
                saveEstimateDTO.cpu.name,
                saveEstimateDTO.motherBoard.name,
                saveEstimateDTO.memory.name,
                saveEstimateDTO.dataStorage.name,
                saveEstimateDTO.graphicsCard.name,
                saveEstimateDTO.cpuCooler.name,
                saveEstimateDTO.powerSupply.name,
                saveEstimateDTO.desktopCase.name)
        )
    }

    @Transactional
    fun updateUserEstimate(id:ObjectId, updatedEstimateDTO: EstimateAnalyzeDTO, userName:String){
        val update = Update()
            .set("cpu", updatedEstimateDTO.cpu.name)
            .set("motherBoard", updatedEstimateDTO.motherBoard.name)
            .set("memory", updatedEstimateDTO.memory.name)
            .set("dataStorage", updatedEstimateDTO.dataStorage.name)
            .set("graphicsCard", updatedEstimateDTO.graphicsCard.name)
            .set("cpuCooler", updatedEstimateDTO.cpuCooler.name)
            .set("powerSupply", updatedEstimateDTO.powerSupply.name)
            .set("desktopCase", updatedEstimateDTO.desktopCase.name)

        val query = Query(Criteria.where(dbStringConfig.mongodb.id).`is`(id)
            .and("userName").`is`(userName)
        )
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
    fun getUserEstimateList(name:String): List<EstimateDTO> {
        val query=Query(Criteria.where(innerStringsConfig.property.userName).`is`(name))
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
                ) }
        return estimateDTOLists
    }

    @Transactional
    fun deleteUserEstimate(estimateId:String,userName:String){
        val query=Query(Criteria.where(dbStringConfig.mongodb.id).`is`(ObjectId(estimateId))
            .and("userName").`is`(userName)
        )
        val result=mongoTemplate.remove(query, EstimateEntity::class.java)

        if(result.deletedCount==dbStringConfig.mongodb.deleteFailValue)
            throw CusComException(CusComErrorCode.FailedDeleteEstimate)
    }

    fun checkDesktopEstimate(estimateAnalyzeDTO: EstimateAnalyzeDTO?){
        if(estimateAnalyzeDTO==null)
            throw CusComException(CusComErrorCode.NotFoundEstimate)

        val caseMaxFormFactor = MotherBoardFormFactor
            .fromString(estimateAnalyzeDTO.desktopCase.motherBoardFormFactor)
        val motherBoardsFormFactor = MotherBoardFormFactor
            .fromString(estimateAnalyzeDTO.motherBoard.motherBoardFormFactor)

        if(estimateAnalyzeDTO.cpuCooler.height >= estimateAnalyzeDTO.desktopCase.cpuCoolerHeight)
            throw CusComException(CusComErrorCode.OversizeCooler)
        if(estimateAnalyzeDTO.graphicsCard.length >= estimateAnalyzeDTO.desktopCase.graphicsCardLength)
            throw CusComException(CusComErrorCode.OversizeGraphicsCard)
        if((innerStringsConfig.parts.memoryInterval-estimateAnalyzeDTO.memory.height) > 0)
            if(estimateAnalyzeDTO.cpuCooler.height+(innerStringsConfig.parts.memoryInterval-estimateAnalyzeDTO.memory.height)
                >= estimateAnalyzeDTO.desktopCase.width)
                throw CusComException(CusComErrorCode.InterferenceMemory)
        if(motherBoardsFormFactor.length>caseMaxFormFactor.length
            || motherBoardsFormFactor.width>caseMaxFormFactor.width)
            throw CusComException(CusComErrorCode.OversizeMotherBoard)
        if(estimateAnalyzeDTO.motherBoard.socket != estimateAnalyzeDTO.cpu.socket)
            throw CusComException(CusComErrorCode.MismatchSocket)
        if(estimateAnalyzeDTO.powerSupply.length > estimateAnalyzeDTO.desktopCase.powerLength)
            throw CusComException(CusComErrorCode.OversizePowerSupply)
        if(estimateAnalyzeDTO.memory.type != estimateAnalyzeDTO.motherBoard.memoryType)
            throw CusComException(CusComErrorCode.MismatchMemory)
        if(estimateAnalyzeDTO.graphicsCard.maxPower
            + estimateAnalyzeDTO.cpu.tdp
            + estimateAnalyzeDTO.cpuCooler.tdp
            >= estimateAnalyzeDTO.powerSupply.power)
            throw CusComException(CusComErrorCode.PowerSupplyShortage)
    }
}