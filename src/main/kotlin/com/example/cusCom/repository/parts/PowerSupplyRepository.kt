package com.example.cusCom.repository.parts

import com.example.cusCom.entity.mySQL.parts.PowerSupplyEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PowerSupplyRepository:JpaRepository<PowerSupplyEntity, Long> {
    fun findByName(name:String): PowerSupplyEntity?
    fun deleteByName(name:String)
}