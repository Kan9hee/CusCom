package com.example.cusCom.estimate.service

import com.example.cusCom.estimate.dto.parts.*
import com.example.cusCom.estimate.repository.parts.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DesktopPartsService(private val caseRepo: CaseRepository,
                          private val cpuCoolerRepo: CPUCoolerRepository,
                          private val cpuRepo: CPURepository,
                          private val dataStorageRepo: DataStorageRepository,
                          private val graphicsCardRepo: GraphicsCardRepository,
                          private val memoryRepo:MemoryRepository,
                          private val motherBoardRepo:MotherBoardRepository,
                          private val powerSupplyRepo:PowerSupplyRepository) {

    @Transactional(readOnly = true)
    fun getCaseList(): List<Case> {
        val caseList:List<Case> = caseRepo.findAll().map{
                entity-> Case(entity.name,
            entity.manufacturer,
            entity.caseType,
            entity.maxMotherBoard,
            entity.maxCoolingFan,
            entity.builtInCoolingFan,
            entity.height,
            entity.length,
            entity.width,
            entity.powerLength,
            entity.cpuCoolerHeight,
            entity.graphicsCardLength)
        }
        return caseList
    }

    @Transactional(readOnly = true)
    fun getCpuCoolerList(): List<CPUCooler> {
        val cpuCoolerList:List<CPUCooler> = cpuCoolerRepo.findAll().map{
                entity->
            CPUCooler(entity.name,
                entity.manufacturer,
                entity.coolingType,
                entity.coolerForm,
                entity.height,
                entity.length,
                entity.width,
                entity.tdp)
        }
        return cpuCoolerList
    }

    @Transactional(readOnly = true)
    fun getCPUList(): List<CPU> {
        val cpuList:List<CPU> = cpuRepo.findAll().map{
                entity->
            CPU(entity.name,
            entity.manufacturer,
            entity.socket,
            entity.memoryType,
            entity.core,
            entity.thread,
            entity.isBuiltInGraphics,
            entity.builtInGraphicName,
            entity.TDP)
        }
        return cpuList
    }

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
        return dataStorageList
    }

    @Transactional(readOnly = true)
    fun getGraphicsCardList(): List<GraphicsCard> {
        val graphicsCardList:List<GraphicsCard> = graphicsCardRepo.findAll().map{
                entity-> GraphicsCard(entity.name,
            entity.manufacturer,
            entity.chipsetManufacturer,
            entity.gpuType,
            entity.length,
            entity.basicPower,
            entity.maxPower,
            entity.phase)
        }
        return graphicsCardList
    }

    @Transactional(readOnly = true)
    fun getMemoryList(): List<Memory> {
        val memoryList:List<Memory> = memoryRepo.findAll().map{
                entity-> Memory(entity.name,
            entity.manufacturer,
            entity.type,
            entity.capacity,
            entity.height)
        }
        return memoryList
    }

    @Transactional(readOnly = true)
    fun getMotherBoardList(): List<MotherBoard> {
        val motherBoardList:List<MotherBoard> = motherBoardRepo.findAll().map{
                entity-> MotherBoard(entity.name,
            entity.manufacturer,
            entity.cpuType,
            entity.socket,
            entity.chipset,
            entity.formFactor,
            entity.memoryType,
            entity.memorySlot,
            entity.ssdM2Slot,
            entity.ssdSATASlot)
        }
        return motherBoardList
    }

    @Transactional(readOnly = true)
    fun getPowerSupplyList(): List<PowerSupply> {
        val powerSupplyList:List<PowerSupply> = powerSupplyRepo.findAll().map{
                entity->PowerSupply(entity.name,
            entity.manufacturer,
            entity.power,
            entity.efficiency,
            entity.modular,
            entity.length)}
        return powerSupplyList
    }
}