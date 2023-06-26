package com.example.cusCom.estimate.repository.parts

import com.example.cusCom.estimate.model.parts.MemoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemoryRepository:JpaRepository<MemoryEntity,String> {
}