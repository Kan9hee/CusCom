package com.example.cusCom.repository.parts

import com.example.cusCom.entity.mySQL.parts.CaseEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CaseRepository: JpaRepository<CaseEntity,Long> {
    fun findByName(name:String): CaseEntity?
    fun deleteByName(name:String)
}