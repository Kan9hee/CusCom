package com.example.cusCom.provideContent.repository.parts

import com.example.cusCom.provideContent.entity.mySQL.parts.MotherBoardEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MotherBoardRepository:JpaRepository<MotherBoardEntity,Long> {
    fun findByName(name:String): Optional<MotherBoardEntity>
}