package com.example.cusCom.estimate.service.parts

import com.example.cusCom.estimate.dto.parts.PowerSupply
import com.example.cusCom.estimate.repository.parts.PowerSupplyRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PowerSupplyService(private val powerSupplyRepo:PowerSupplyRepository) {

    @Transactional(readOnly = true)
    fun getPowerSupplyList(): List<PowerSupply> {
        val powerSupplyList:List<PowerSupply> = powerSupplyRepo.findAll().map{
                entity->PowerSupply(entity.name,
                                    entity.manufacturer,
                                    entity.power,
                                    entity.efficiency,
                                    entity.modular,
                                    entity.length)}
        for(e:PowerSupply in powerSupplyList)
            println("this power is ${e.name}")
        return powerSupplyList
    }
}