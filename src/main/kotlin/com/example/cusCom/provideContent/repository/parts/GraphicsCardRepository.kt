package com.example.cusCom.provideContent.repository.parts

import com.example.cusCom.provideContent.dto.parts.GraphicsCardEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GraphicsCardRepository:JpaRepository<GraphicsCardEntity,String> {
}