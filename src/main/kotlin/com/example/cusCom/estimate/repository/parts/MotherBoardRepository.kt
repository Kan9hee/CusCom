package com.example.cusCom.estimate.repository.parts

import com.example.cusCom.estimate.model.parts.MotherBoardEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MotherBoardRepository:JpaRepository<MotherBoardEntity,String> {
}