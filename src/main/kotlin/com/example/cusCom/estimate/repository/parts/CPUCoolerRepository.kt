package com.example.cusCom.estimate.repository.parts

import com.example.cusCom.estimate.model.parts.CPUCoolerEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CPUCoolerRepository:JpaRepository<CPUCoolerEntity,String> {
}