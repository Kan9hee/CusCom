package com.example.cusCom.estimate.repository

import com.example.cusCom.estimate.entity.EstimateEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EstimateRepository:JpaRepository<EstimateEntity,Long>{
    fun findOrderEntityById(Id:Long):EstimateEntity?
    fun deleteEstimateEntityById(Id:Long)
}