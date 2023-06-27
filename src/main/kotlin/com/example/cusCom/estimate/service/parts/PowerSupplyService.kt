package com.example.cusCom.estimate.service.parts

import com.example.cusCom.estimate.model.parts.PowerSupplyEntity
import com.example.cusCom.estimate.repository.parts.PowerSupplyRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PowerSupplyService(private val powerSupplyRepo:PowerSupplyRepository) {

    @Transactional(readOnly = true)
    fun getPowerSupplyList(){
        val powerSupplyList:List<PowerSupplyEntity> = powerSupplyRepo.findAll()
        for(e:PowerSupplyEntity in powerSupplyList)
            println("this power is ${e.name}")
    }
}