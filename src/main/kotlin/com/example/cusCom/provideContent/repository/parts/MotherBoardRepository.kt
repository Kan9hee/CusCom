package com.example.cusCom.provideContent.repository.parts

import com.example.cusCom.provideContent.entity.parts.MotherBoardEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MotherBoardRepository:JpaRepository<MotherBoardEntity,String> {
}