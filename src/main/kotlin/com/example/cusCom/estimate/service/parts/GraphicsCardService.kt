package com.example.cusCom.estimate.service.parts

import com.example.cusCom.estimate.dto.parts.GraphicsCard
import com.example.cusCom.estimate.repository.parts.GraphicsCardRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GraphicsCardService(private val graphicsCardRepo:GraphicsCardRepository) {

    @Transactional(readOnly = true)
    fun getGraphicsCardList(): List<GraphicsCard> {
        val graphicsCardList:List<GraphicsCard> = graphicsCardRepo.findAll().map{
                entity-> GraphicsCard(entity.name,
            entity.manufacturer,
            entity.chipsetManufacturer,
            entity.gpuType,
            entity.length,
            entity.basicPower,
            entity.maxPower,
            entity.phase)
        }
        for(e: GraphicsCard in graphicsCardList)
            println("this GraphicsCard is ${e.name}")
        return graphicsCardList
    }
}