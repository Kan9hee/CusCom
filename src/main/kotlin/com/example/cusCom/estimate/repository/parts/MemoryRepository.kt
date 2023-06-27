package com.example.cusCom.estimate.repository.parts

import com.example.cusCom.estimate.dto.parts.MemoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemoryRepository:JpaRepository<MemoryEntity,String> {
}