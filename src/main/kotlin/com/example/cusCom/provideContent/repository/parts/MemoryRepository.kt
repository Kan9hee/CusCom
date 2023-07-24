package com.example.cusCom.provideContent.repository.parts

import com.example.cusCom.provideContent.dto.parts.MemoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemoryRepository:JpaRepository<MemoryEntity,String> {
}