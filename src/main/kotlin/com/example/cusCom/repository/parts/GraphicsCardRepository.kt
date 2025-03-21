package com.example.cusCom.repository.parts

import com.example.cusCom.entity.mySQL.parts.GraphicsCardEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GraphicsCardRepository:JpaRepository<GraphicsCardEntity,Long> {
    fun findByName(name:String): GraphicsCardEntity?
    fun deleteByName(name:String)
}