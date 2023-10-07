package com.example.cusCom.provideContent.repository.parts

import com.example.cusCom.provideContent.entity.mySQL.parts.DataStorageEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DataStorageRepository:JpaRepository<DataStorageEntity,Long> {
    fun findByName(name:String): Optional<DataStorageEntity>
}