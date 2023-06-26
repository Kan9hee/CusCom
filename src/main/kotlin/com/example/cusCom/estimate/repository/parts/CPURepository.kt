package com.example.cusCom.estimate.repository.parts

import com.example.cusCom.estimate.model.parts.CPUEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CPURepository:JpaRepository<CPUEntity,String> {
}