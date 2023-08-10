package com.example.cusCom.provideContent.service

import com.example.cusCom.provideContent.dto.parts.*
import com.example.cusCom.provideContent.entity.parts.*
import com.example.cusCom.provideContent.repository.parts.*
import jakarta.persistence.EntityNotFoundException
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
        val caseList:List<Case> = caseRepo.findAll()
            .filter{ entity -> entity.name != "sample" }
            .map{
                entity:CaseEntity -> Case(entity.name,
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
                    entity.graphicsCardLength,
                    entity.imageUrl) }
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
            entity.graphicsCardLength,
            entity.imageUrl)
    }

    @Transactional
    fun loadSampleCase(): Case {
        val entity = caseRepo.findById("Sample").orElseThrow { EntityNotFoundException("샘플 데이터를 찾을 수 없습니다.") }
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
            entity.graphicsCardLength,
            entity.imageUrl)
    }

    @Transactional
    fun createCase(case: Case){
        caseRepo.save(CaseEntity(case))
    }

    @Transactional
    fun updateCase(case: Case){
        val temp=caseRepo.findById(case.name).get()
        temp.update(case)
    }

    @Transactional
    fun deleteCase(case: String){
        return caseRepo.deleteById(case)
    }


    @Transactional(readOnly = true)
    fun getCpuCoolerList(): List<CPUCooler> {
        val cpuCoolerList:List<CPUCooler> = cpuCoolerRepo.findAll()
            .filter{ entity -> entity.name != "sample" }
            .map{
                entity:CPUCoolerEntity-> CPUCooler(entity.name,
                    entity.imageUrl,
                    entity.manufacturer,
                    entity.coolingType,
                    entity.coolerForm,
                    entity.height,
                    entity.length,
                    entity.width,
                    entity.tdp) }
        return cpuCoolerList
    }

    @Transactional
    fun findCpuCooler(name:String): CPUCooler {
        val entity = cpuCoolerRepo.findById(name).orElseThrow { EntityNotFoundException("CPUCooler ${name}을 찾을 수가 없습니다.") }
        return CPUCooler(
            entity.name,
            entity.imageUrl,
            entity.manufacturer,
            entity.coolingType,
            entity.coolerForm,
            entity.height,
            entity.length,
            entity.width,
            entity.tdp)
    }

    @Transactional
    fun loadSampleCpuCooler(): CPUCooler {
        val entity = cpuCoolerRepo.findById("Sample").orElseThrow { EntityNotFoundException("샘플 데이터를 찾을 수 없습니다.") }
        return CPUCooler(
            entity.name,
            entity.imageUrl,
            entity.manufacturer,
            entity.coolingType,
            entity.coolerForm,
            entity.height,
            entity.length,
            entity.width,
            entity.tdp)
    }

    @Transactional
    fun createCPUCooler(cpuCooler: CPUCooler){
        cpuCoolerRepo.save(CPUCoolerEntity(cpuCooler))
    }

    @Transactional
    fun updateCPUCooler(cpuCooler: CPUCooler){
        var temp=cpuCoolerRepo.findById(cpuCooler.name).get()
        temp.update(cpuCooler)
    }

    @Transactional
    fun deleteCPUCooler(cpuCooler: String){
        return cpuCoolerRepo.deleteById(cpuCooler)
    }


    @Transactional(readOnly = true)
    fun getCPUList(): List<CPU> {
        val cpuList:List<CPU> = cpuRepo.findAll()
            .filter{ entity -> entity.name != "sample" }
            .map{
                entity:CPUEntity -> CPU(entity.name,
                    entity.imageUrl,
                    entity.manufacturer,
                    entity.socket,
                    entity.memoryType,
                    entity.core,
                    entity.thread,
                    entity.isBuiltInGraphics,
                    entity.builtInGraphicName,
                    entity.TDP) }
        return cpuList
    }

    @Transactional
    fun findCpu(name: String): CPU {
        val entity = cpuRepo.findById(name).orElseThrow { EntityNotFoundException("CPU ${name}을 찾을 수가 없습니다.") }
        return CPU(
            entity.name,
            entity.imageUrl,
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
    fun loadSampleCpu(): CPU {
        val entity = cpuRepo.findById("Sample").orElseThrow { EntityNotFoundException("샘플 데이터를 찾을 수 없습니다.") }
        return CPU(
            entity.name,
            entity.imageUrl,
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
    fun createCPU(cpu: CPU){
        cpuRepo.save(CPUEntity(cpu))
    }

    @Transactional
    fun updateCPU(cpu: CPU){
        var temp=cpuRepo.findById(cpu.name).get()
        temp.update(cpu)
    }

    @Transactional
    fun deleteCPU(cpu: String){
        return cpuRepo.deleteById(cpu)
    }


    @Transactional(readOnly = true)
    fun getDataStorageList(): List<DataStorage> {
        val dataStorageList:List<DataStorage> = dataStorageRepo.findAll()
            .filter{ entity -> entity.name != "sample" }
            .map{
                entity:DataStorageEntity -> DataStorage(entity.name,
                    entity.imageUrl,
                    entity.manufacturer,
                    entity.storageInterface,
                    entity.formFactor,
                    entity.capacity,
                    entity.readSpeed,
                    entity.writeSpeed) }
        return dataStorageList
    }

    @Transactional
    fun findDataStorage(name:String): DataStorage {
        val entity = dataStorageRepo.findById(name).orElseThrow { EntityNotFoundException("DataStorage ${name}을 찾을 수가 없습니다.") }
        return DataStorage(entity.name,
            entity.imageUrl,
            entity.manufacturer,
            entity.storageInterface,
            entity.formFactor,
            entity.capacity,
            entity.readSpeed,
            entity.writeSpeed)
    }

    @Transactional
    fun loadSampleDataStorage(): DataStorage {
        val entity = dataStorageRepo.findById("Sample").orElseThrow { EntityNotFoundException("샘플 데이터를 찾을 수 없습니다.") }
        return DataStorage(entity.name,
            entity.imageUrl,
            entity.manufacturer,
            entity.storageInterface,
            entity.formFactor,
            entity.capacity,
            entity.readSpeed,
            entity.writeSpeed)
    }

    @Transactional
    fun createDataStorage(dataStorage: DataStorage) {
        dataStorageRepo.save(DataStorageEntity(dataStorage))
    }

    @Transactional
    fun updateDataStorage(dataStorage: DataStorage){
        var temp=dataStorageRepo.findById(dataStorage.name).get()
        temp.update(dataStorage)
    }

    @Transactional
    fun deleteDataStorage(dataStorage: String){
        return dataStorageRepo.deleteById(dataStorage)
    }


    @Transactional(readOnly = true)
    fun getGraphicsCardList(): List<GraphicsCard> {
        val graphicsCardList:List<GraphicsCard> = graphicsCardRepo.findAll()
            .filter{ entity -> entity.name != "sample" }
            .map{
                entity:GraphicsCardEntity -> GraphicsCard(entity.name,
                    entity.imageUrl,
                    entity.manufacturer,
                    entity.chipsetManufacturer,
                    entity.gpuType,
                    entity.length,
                    entity.basicPower,
                    entity.maxPower,
                    entity.phase) }
        return graphicsCardList
    }

    @Transactional
    fun findGraphicsCard(name:String): GraphicsCard {
        val entity = graphicsCardRepo.findById(name).orElseThrow { EntityNotFoundException("GraphicsCard ${name}을 찾을 수가 없습니다.") }
        return GraphicsCard(entity.name,
            entity.imageUrl,
            entity.manufacturer,
            entity.chipsetManufacturer,
            entity.gpuType,
            entity.length,
            entity.basicPower,
            entity.maxPower,
            entity.phase)
    }

    @Transactional
    fun loadSampleGraphicsCard(): GraphicsCard {
        val entity = graphicsCardRepo.findById("Sample").orElseThrow { EntityNotFoundException("샘플 데이터를 찾을 수 없습니다.") }
        return GraphicsCard(entity.name,
            entity.imageUrl,
            entity.manufacturer,
            entity.chipsetManufacturer,
            entity.gpuType,
            entity.length,
            entity.basicPower,
            entity.maxPower,
            entity.phase)
    }

    @Transactional
    fun createGraphicsCard(graphicsCard: GraphicsCard) {
        graphicsCardRepo.save(GraphicsCardEntity(graphicsCard))
    }

    @Transactional
    fun updateGraphicsCard(graphicsCard: GraphicsCard){
        var temp=graphicsCardRepo.findById(graphicsCard.name).get()
        temp.update(graphicsCard)
    }

    @Transactional
    fun deleteGraphicsCard(graphicsCard: String){
        return graphicsCardRepo.deleteById(graphicsCard)
    }


    @Transactional(readOnly = true)
    fun getMemoryList(): List<Memory> {
        val memoryList:List<Memory> = memoryRepo.findAll()
            .filter{ entity -> entity.name != "sample" }
            .map{
                entity:MemoryEntity -> Memory(entity.name,
                    entity.imageUrl,
                    entity.manufacturer,
                    entity.type,
                    entity.capacity,
                    entity.height) }
        return memoryList
    }

    @Transactional
    fun findMemory(name:String): Memory {
        val entity = memoryRepo.findById(name).orElseThrow { EntityNotFoundException("Memory ${name}을 찾을 수가 없습니다.") }
        return Memory(entity.name,
            entity.imageUrl,
            entity.manufacturer,
            entity.type,
            entity.capacity,
            entity.height)
    }

    @Transactional
    fun loadSampleMemory(): Memory {
        val entity = memoryRepo.findById("Sample").orElseThrow { EntityNotFoundException("샘플 데이터를 찾을 수 없습니다.") }
        return Memory(entity.name,
            entity.imageUrl,
            entity.manufacturer,
            entity.type,
            entity.capacity,
            entity.height)
    }

    @Transactional
    fun createMemory(memory: Memory){
        memoryRepo.save(MemoryEntity(memory))
    }

    @Transactional
    fun updateMemory(memory: Memory){
        var temp=memoryRepo.findById(memory.name).get()
        temp.update(memory)
    }

    @Transactional
    fun deleteMemory(memory: String){
        return memoryRepo.deleteById(memory)
    }


    @Transactional(readOnly = true)
    fun getMotherBoardList(): List<MotherBoard> {
        val motherBoardList:List<MotherBoard> = motherBoardRepo.findAll()
            .filter{ entity -> entity.name != "sample" }
            .map{
                entity:MotherBoardEntity -> MotherBoard(entity.name,
                    entity.imageUrl,
                    entity.manufacturer,
                    entity.cpuType,
                    entity.socket,
                    entity.chipset,
                    entity.formFactor,
                    entity.memoryType,
                    entity.memorySlot,
                    entity.ssdM2Slot,
                    entity.ssdSATASlot) }
        return motherBoardList
    }

    @Transactional
    fun findMotherBoard(name:String): MotherBoard {
        val entity = motherBoardRepo.findById(name).orElseThrow { EntityNotFoundException("MotherBoard ${name}을 찾을 수가 없습니다.") }
        return MotherBoard(entity.name,
            entity.imageUrl,
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
    fun loadSampleMotherBoard(): MotherBoard {
        val entity = motherBoardRepo.findById("Sample").orElseThrow { EntityNotFoundException("샘플 데이터를 찾을 수 없습니다.") }
        return MotherBoard(entity.name,
            entity.imageUrl,
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
    fun createMotherBoard(motherBoard: MotherBoard){
        motherBoardRepo.save(MotherBoardEntity(motherBoard))
    }

    @Transactional
    fun updateMotherBoard(motherBoard: MotherBoard){
        var temp=motherBoardRepo.findById(motherBoard.name).get()
        temp.update(motherBoard)
    }

    @Transactional
    fun deleteMotherBoard(motherBoard: String){
        return motherBoardRepo.deleteById(motherBoard)
    }


    @Transactional(readOnly = true)
    fun getPowerSupplyList(): List<PowerSupply> {
        val powerSupplyList:List<PowerSupply> = powerSupplyRepo.findAll()
            .filter{ entity -> entity.name != "sample" }
            .map{
                entity:PowerSupplyEntity -> PowerSupply(entity.name,
                    entity.imageUrl,
                    entity.manufacturer,
                    entity.power,
                    entity.efficiency,
                    entity.modular,
                    entity.length) }
        return powerSupplyList
    }

    @Transactional
    fun findPowerSupply(name:String): PowerSupply {
        val entity = powerSupplyRepo.findById(name).orElseThrow { EntityNotFoundException("PowerSupply ${name}을 찾을 수가 없습니다.") }
        return PowerSupply(entity.name,
            entity.imageUrl,
            entity.manufacturer,
            entity.power,
            entity.efficiency,
            entity.modular,
            entity.length)
    }

    @Transactional
    fun loadSamplePowerSupply(): PowerSupply {
        val entity = powerSupplyRepo.findById("Sample").orElseThrow { EntityNotFoundException("샘플 데이터를 찾을 수 없습니다.") }
        return PowerSupply(entity.name,
            entity.imageUrl,
            entity.manufacturer,
            entity.power,
            entity.efficiency,
            entity.modular,
            entity.length)
    }

    @Transactional
    fun createPowerSupply(powerSupply: PowerSupply){
        powerSupplyRepo.save(PowerSupplyEntity(powerSupply))
    }

    @Transactional
    fun updatePowerSupply(powerSupply: PowerSupply){
        var temp=powerSupplyRepo.findById(powerSupply.name).get()
        temp.update(powerSupply)
    }

    @Transactional
    fun deletePowerSupply(powerSupply: String){
        return powerSupplyRepo.deleteById(powerSupply)
    }
}