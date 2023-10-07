package com.example.cusCom.provideContent.repository.parts

import com.example.cusCom.provideContent.entity.mySQL.parts.CaseEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CaseRepository: JpaRepository<CaseEntity,Long> {
    fun findByName(name:String): Optional<CaseEntity>
}