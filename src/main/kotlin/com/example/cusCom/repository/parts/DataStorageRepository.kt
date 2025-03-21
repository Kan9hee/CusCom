package com.example.cusCom.repository.parts

import com.example.cusCom.entity.mySQL.parts.DataStorageEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DataStorageRepository:JpaRepository<DataStorageEntity,Long> {
    fun findByName(name:String): DataStorageEntity?
    fun deleteByName(name:String)
}