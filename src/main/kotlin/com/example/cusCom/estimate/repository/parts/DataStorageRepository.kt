package com.example.cusCom.estimate.repository.parts

import com.example.cusCom.estimate.dto.parts.DataStorageEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DataStorageRepository:JpaRepository<DataStorageEntity,String> {
}