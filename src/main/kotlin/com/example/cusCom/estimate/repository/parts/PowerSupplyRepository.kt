package com.example.cusCom.estimate.repository.parts

import com.example.cusCom.estimate.model.parts.PowerSupplyEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PowerSupplyRepository:JpaRepository<PowerSupplyEntity, String> {
}