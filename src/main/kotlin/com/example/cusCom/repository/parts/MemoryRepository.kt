package com.example.cusCom.repository.parts

import com.example.cusCom.entity.mySQL.parts.MemoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemoryRepository:JpaRepository<MemoryEntity,Long> {
    fun findByName(name:String): MemoryEntity?
    fun deleteByName(name:String)
}