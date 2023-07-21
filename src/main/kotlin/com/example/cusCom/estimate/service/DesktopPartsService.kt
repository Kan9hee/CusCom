package com.example.cusCom.estimate.service

import com.example.cusCom.estimate.dto.parts.*
import com.example.cusCom.estimate.repository.parts.*
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

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
    fun findCase(name:String): Case {
        val entity = caseRepo.findById(name).orElseThrow { EntityNotFoundException("Case ${name}을 찾을 수가 없습니다.") }
        return Case(entity.name,
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

    @Transactional
    fun createCase(case: Case): CaseEntity {
        return caseRepo.save(case.toCaseEntity())
    }

    @Transactional
    fun updateCase(case: Case): CaseEntity {
        val temp=caseRepo.findById(case.name).get()
        temp.update(case)
        return temp
    }

    @Transactional
    fun deleteCase(case: String){
        return caseRepo.deleteById(case)
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
    fun findCpuCooler(name:String): CPUCooler {
        val entity = cpuCoolerRepo.findById(name).orElseThrow { EntityNotFoundException("CPUCooler ${name}을 찾을 수가 없습니다.") }
        return CPUCooler(
            entity.name,
            entity.manufacturer,
            entity.coolingType,
            entity.coolerForm,
            entity.height,
            entity.length,
            entity.width,
            entity.tdp)
    }

    @Transactional
    fun createCPUCooler(cpuCooler: CPUCooler): CPUCoolerEntity {
        return cpuCoolerRepo.save(cpuCooler.toCPUCoolerEntity())
    }

    @Transactional
    fun updateCPUCooler(cpuCooler: CPUCooler): CPUCoolerEntity {
        var temp=cpuCoolerRepo.findById(cpuCooler.name).get()
        temp.update(cpuCooler)
        return temp
    }

    @Transactional
    fun deleteCPUCooler(cpuCooler: String){
        return cpuCoolerRepo.deleteById(cpuCooler)
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
    fun findCpu(name: String): CPU {
        val entity = cpuRepo.findById(name).orElseThrow { EntityNotFoundException("CPU ${name}을 찾을 수가 없습니다.") }
        return CPU(
            entity.name,
            entity.manufacturer,
            entity.socket,
            entity.memoryType,
            entity.core,
            entity.thread,
            entity.isBuiltInGraphics,
            entity.builtInGraphicName,
            entity.TDP
        )
    }

    @Transactional
    fun createCPU(cpu: CPU): CPUEntity {
        return cpuRepo.save(cpu.toCPUEntity())
    }

    @Transactional
    fun updateCPU(cpu: CPU): CPUEntity {
        var temp=cpuRepo.findById(cpu.name).get()
        temp.update(cpu)
        return temp
    }

    @Transactional
    fun deleteCPU(cpu: String){
        return cpuRepo.deleteById(cpu)
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
    fun findDataStorage(name:String): DataStorage {
        val entity = dataStorageRepo.findById(name).orElseThrow { EntityNotFoundException("DataStorage ${name}을 찾을 수가 없습니다.") }
        return DataStorage(entity.name,
            entity.manufacturer,
            entity.storageInterface,
            entity.formFactor,
            entity.capacity,
            entity.readSpeed,
            entity.writeSpeed)
    }

    @Transactional
    fun createDataStorage(dataStorage: DataStorage): DataStorageEntity {
        return dataStorageRepo.save(dataStorage.toDataStorageEntity())
    }

    @Transactional
    fun updateDataStorage(dataStorage: DataStorage): DataStorageEntity {
        var temp=dataStorageRepo.findById(dataStorage.name).get()
        temp.update(dataStorage)
        return temp
    }

    @Transactional
    fun deleteDataStorage(dataStorage: String){
        return dataStorageRepo.deleteById(dataStorage)
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
    fun findGraphicsCard(name:String): GraphicsCard {
        val entity = graphicsCardRepo.findById(name).orElseThrow { EntityNotFoundException("GraphicsCard ${name}을 찾을 수가 없습니다.") }
        return GraphicsCard(entity.name,
            entity.manufacturer,
            entity.chipsetManufacturer,
            entity.gpuType,
            entity.length,
            entity.basicPower,
            entity.maxPower,
            entity.phase)
    }

    @Transactional
    fun createGraphicsCard(graphicsCard: GraphicsCard): GraphicsCardEntity {
        return graphicsCardRepo.save(graphicsCard.toGraphicsCardEntity())
    }

    @Transactional
    fun updateGraphicsCard(graphicsCard: GraphicsCard): GraphicsCardEntity {
        var temp=graphicsCardRepo.findById(graphicsCard.name).get()
        temp.update(graphicsCard)
        return temp
    }

    @Transactional
    fun deleteGraphicsCard(graphicsCard: String){
        return graphicsCardRepo.deleteById(graphicsCard)
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
    fun findMemory(name:String): Memory {
        val entity = memoryRepo.findById(name).orElseThrow { EntityNotFoundException("Memory ${name}을 찾을 수가 없습니다.") }
        return Memory(entity.name,
            entity.manufacturer,
            entity.type,
            entity.capacity,
            entity.height)
    }

    @Transactional
    fun createMemory(memory: Memory): MemoryEntity {
        return memoryRepo.save(memory.toMemoryEntity())
    }

    @Transactional
    fun updateMemory(memory: Memory): MemoryEntity {
        var temp=memoryRepo.findById(memory.name).get()
        temp.update(memory)
        return temp
    }

    @Transactional
    fun deleteMemory(memory: String){
        return memoryRepo.deleteById(memory)
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
    fun findMotherBoard(name:String): MotherBoard {
        val entity = motherBoardRepo.findById(name).orElseThrow { EntityNotFoundException("MotherBoard ${name}을 찾을 수가 없습니다.") }
        return MotherBoard(entity.name,
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

    @Transactional
    fun createMotherBoard(motherBoard: MotherBoard): MotherBoardEntity {
        return motherBoardRepo.save(motherBoard.toMotherBoardEntity())
    }

    @Transactional
    fun updateMotherBoard(motherBoard: MotherBoard): MotherBoardEntity {
        var temp=motherBoardRepo.findById(motherBoard.name).get()
        temp.update(motherBoard)
        return temp
    }

    @Transactional
    fun deleteMotherBoard(motherBoard: String){
        return motherBoardRepo.deleteById(motherBoard)
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
    fun findPowerSupply(name:String): PowerSupply {
        val entity = powerSupplyRepo.findById(name).orElseThrow { EntityNotFoundException("PowerSupply ${name}을 찾을 수가 없습니다.") }
        return PowerSupply(entity.name,
            entity.manufacturer,
            entity.power,
            entity.efficiency,
            entity.modular,
            entity.length)
    }

    @Transactional
    fun createPowerSupply(powerSupply: PowerSupply): PowerSupplyEntity {
        return powerSupplyRepo.save(powerSupply.toPowerSupplyEntity())
    }

    @Transactional
    fun updatePowerSupply(powerSupply: PowerSupply): PowerSupplyEntity {
        var temp=powerSupplyRepo.findById(powerSupply.name).get()
        temp.update(powerSupply)
        return temp
    }

    @Transactional
    fun deletePowerSupply(powerSupply: String){
        return powerSupplyRepo.deleteById(powerSupply)
    }
}