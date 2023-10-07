package com.example.cusCom.provideContent.repository.parts

import com.example.cusCom.provideContent.entity.mySQL.parts.CPUEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CPURepository:JpaRepository<CPUEntity,Long> {
    fun findByName(name:String): Optional<CPUEntity>
}