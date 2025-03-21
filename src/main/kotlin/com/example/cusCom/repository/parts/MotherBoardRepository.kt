package com.example.cusCom.repository.parts

import com.example.cusCom.entity.mySQL.parts.MotherBoardEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MotherBoardRepository:JpaRepository<MotherBoardEntity,Long> {
    fun findByName(name:String): MotherBoardEntity?
    fun deleteByName(name:String)
}