package com.example.cusCom.provideContent.repository.parts

import com.example.cusCom.provideContent.entity.mySQL.parts.CPUCoolerEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CPUCoolerRepository:JpaRepository<CPUCoolerEntity,Long> {
    fun findByName(name:String): Optional<CPUCoolerEntity>
}