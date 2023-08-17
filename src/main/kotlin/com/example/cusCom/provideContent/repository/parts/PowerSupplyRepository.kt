package com.example.cusCom.provideContent.repository.parts

import com.example.cusCom.provideContent.entity.mySQL.parts.PowerSupplyEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PowerSupplyRepository:JpaRepository<PowerSupplyEntity, String> {
}