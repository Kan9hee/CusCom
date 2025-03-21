package com.example.cusCom.repository.parts

import com.example.cusCom.entity.mySQL.parts.CPUEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CPURepository:JpaRepository<CPUEntity,Long> {
    fun findByName(name:String): CPUEntity?
    fun deleteByName(name:String)
}