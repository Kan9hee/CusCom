package com.example.cusCom.provideContent.repository.parts

import com.example.cusCom.provideContent.dto.parts.DataStorageEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DataStorageRepository:JpaRepository<DataStorageEntity,String> {
}