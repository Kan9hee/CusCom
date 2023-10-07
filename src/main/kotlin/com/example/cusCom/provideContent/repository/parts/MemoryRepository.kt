package com.example.cusCom.provideContent.repository.parts

import com.example.cusCom.provideContent.entity.mySQL.parts.MemoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MemoryRepository:JpaRepository<MemoryEntity,Long> {
    fun findByName(name:String): Optional<MemoryEntity>
}