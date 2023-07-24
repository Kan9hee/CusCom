package com.example.cusCom.provideContent.repository.parts

import com.example.cusCom.provideContent.entity.parts.CaseEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CaseRepository: JpaRepository<CaseEntity,String> {
}