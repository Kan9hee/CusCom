package com.example.cusCom.provideContent.repository

import com.example.cusCom.provideContent.entity.mySQL.MotherBoardFormFactorEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MotherBoardFormFactorRepository: JpaRepository<MotherBoardFormFactorEntity, String> {
}