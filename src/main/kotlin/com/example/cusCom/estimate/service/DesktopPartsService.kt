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

    @Transactional
    fun createCase(case: Case): CaseEntity {
        return caseRepo.save(case.toCaseEntity())
    }

    @Transactional
    fun updateCase(case: Case): CaseEntity {
        var temp=caseRepo.findById(case.name).get()
        temp=case.toCaseEntity()
        return temp
    }

    @Transactional
    fun deleteCase(case: Case){
        return caseRepo.deleteById(case.name)
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

    @Transactional
    fun createCPUCooler(cpuCooler: CPUCooler): CPUCoolerEntity {
        return cpuCoolerRepo.save(cpuCooler.toCPUCoolerEntity())
    }

    @Transactional
    fun updateCPUCooler(cpuCooler: CPUCooler): CPUCoolerEntity {
        var temp=cpuCoolerRepo.findById(cpuCooler.name).get()
        temp=cpuCooler.toCPUCoolerEntity()
        return temp
    }

    @Transactional
    fun deleteCPUCooler(cpuCooler: CPUCooler){
        return cpuCoolerRepo.deleteById(cpuCooler.name)
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

    @Transactional
    fun createCPU(cpu: CPU): CPUEntity {
        return cpuRepo.save(cpu.toCPUEntity())
    }

    @Transactional
    fun updateCPU(cpu: CPU): CPUEntity {
        var temp=cpuRepo.findById(cpu.name).get()
        temp=cpu.toCPUEntity()
        return temp
    }

    @Transactional
    fun deleteCPU(cpu: CPU){
        return cpuRepo.deleteById(cpu.name)
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

    @Transactional
    fun createDataStorage(dataStorage: DataStorage): DataStorageEntity {
        return dataStorageRepo.save(dataStorage.toDataStorageEntity())
    }

    @Transactional
    fun updateDataStorage(dataStorage: DataStorage): DataStorageEntity {
        var temp=dataStorageRepo.findById(dataStorage.name).get()
        temp=dataStorage.toDataStorageEntity()
        return temp
    }

    @Transactional
    fun deleteDataStorage(dataStorage: DataStorage){
        return dataStorageRepo.deleteById(dataStorage.name)
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

    @Transactional
    fun createGraphicsCard(graphicsCard: GraphicsCard): GraphicsCardEntity {
        return graphicsCardRepo.save(graphicsCard.toGraphicsCardEntity())
    }

    @Transactional
    fun updateGraphicsCard(graphicsCard: GraphicsCard): GraphicsCardEntity {
        var temp=graphicsCardRepo.findById(graphicsCard.name).get()
        temp=graphicsCard.toGraphicsCardEntity()
        return temp
    }

    @Transactional
    fun deleteGraphicsCard(graphicsCard: GraphicsCard){
        return graphicsCardRepo.deleteById(graphicsCard.name)
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

    @Transactional
    fun createMemory(memory: Memory): MemoryEntity {
        return memoryRepo.save(memory.toMemoryEntity())
    }

    @Transactional
    fun updateMemory(memory: Memory): MemoryEntity {
        var temp=memoryRepo.findById(memory.name).get()
        temp=memory.toMemoryEntity()
        return temp
    }

    @Transactional
    fun deleteMemory(memory: Memory){
        return memoryRepo.deleteById(memory.name)
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

    @Transactional
    fun createMotherBoard(motherBoard: MotherBoard): MotherBoardEntity {
        return motherBoardRepo.save(motherBoard.toMotherBoardEntity())
    }

    @Transactional
    fun updateMotherBoard(motherBoard: MotherBoard): MotherBoardEntity {
        var temp=motherBoardRepo.findById(motherBoard.name).get()
        temp=motherBoard.toMotherBoardEntity()
        return temp
    }

    @Transactional
    fun deleteMotherBoard(motherBoard: MotherBoard){
        return motherBoardRepo.deleteById(motherBoard.name)
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

    @Transactional
    fun createPowerSupply(powerSupply: PowerSupply): PowerSupplyEntity {
        return powerSupplyRepo.save(powerSupply.toPowerSupplyEntity())
    }

    @Transactional
    fun updatePowerSupply(powerSupply: PowerSupply): PowerSupplyEntity {
        var temp=powerSupplyRepo.findById(powerSupply.name).get()
        temp=powerSupply.toPowerSupplyEntity()
        return temp
    }

    @Transactional
    fun deletePowerSupply(powerSupply: PowerSupply){
        return powerSupplyRepo.deleteById(powerSupply.name)
    }
}