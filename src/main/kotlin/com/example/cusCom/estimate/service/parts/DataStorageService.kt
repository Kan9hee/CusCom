package com.example.cusCom.estimate.service.parts

import com.example.cusCom.estimate.dto.parts.DataStorage
import com.example.cusCom.estimate.repository.parts.DataStorageRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DataStorageService(private val dataStorageRepo:DataStorageRepository) {

    @Transactional(readOnly = true)
    fun getDataStorageList(): List<DataStorage> {
        val dataStorageList:List<DataStorage> = dataStorageRepo.findAll().map{
                entity->
            DataStorage(entity.name,
                entity.manufacturer,
                entity.storageInterface,
                entity.formFactor,
                entity.capacity,
                entity.readSpeed,
                entity.writeSpeed)
        }
        for(e: DataStorage in dataStorageList)
            println("this DataStorage is ${e.name}")
        return dataStorageList
    }
}