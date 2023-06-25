package com.example.cusCom.estimate.repository

import com.example.cusCom.estimate.entity.MotherBoardFormFactorEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MotherBoardFormFactorRepository: JpaRepository<MotherBoardFormFactorEntity, String> {
}