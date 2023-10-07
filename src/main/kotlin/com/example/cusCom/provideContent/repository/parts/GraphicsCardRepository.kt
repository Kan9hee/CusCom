package com.example.cusCom.provideContent.repository.parts

import com.example.cusCom.provideContent.entity.mySQL.parts.GraphicsCardEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface GraphicsCardRepository:JpaRepository<GraphicsCardEntity,Long> {
    fun findByName(name:String): Optional<GraphicsCardEntity>
}