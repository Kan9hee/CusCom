package com.example.cusCom.provideContent.repository

import com.example.cusCom.provideContent.entity.mySQL.CPUSocketEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CPUSocketRepository: JpaRepository<CPUSocketEntity, String> {
}