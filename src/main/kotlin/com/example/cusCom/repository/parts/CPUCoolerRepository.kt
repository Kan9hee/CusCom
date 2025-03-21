package com.example.cusCom.repository.parts

import com.example.cusCom.entity.mySQL.parts.CPUCoolerEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CPUCoolerRepository:JpaRepository<CPUCoolerEntity,Long> {
    fun findByName(name:String): CPUCoolerEntity?
    fun deleteByName(name:String)
}