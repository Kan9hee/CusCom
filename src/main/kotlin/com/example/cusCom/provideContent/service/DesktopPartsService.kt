package com.example.cusCom.provideContent.service

import com.example.cusCom.exception.CusComErrorCode
import com.example.cusCom.exception.CusComException
import com.example.cusCom.provideContent.dto.MotherBoardFormFactor
import com.example.cusCom.provideContent.dto.parts.*
import com.example.cusCom.provideContent.entity.mySQL.parts.*
import com.example.cusCom.provideContent.repository.CPUSocketRepository
import com.example.cusCom.provideContent.repository.MotherBoardFormFactorRepository
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
                          private val powerSupplyRepo:PowerSupplyRepository,
                          private val motherBoardFormFactorRepo: MotherBoardFormFactorRepository,
                          private val cpuSocketRepository: CPUSocketRepository) {

    @Transactional(readOnly = true)
    fun getCaseList(): List<Case> {
        val caseList:List<Case> = caseRepo.findAll()
            .map{
                entity: CaseEntity -> Case(
                    entity.id,
                    entity.name,
                    entity.manufacturer,
                    entity.caseType,
                    MotherBoardFormFactor(entity.motherBoardFormFactor.name,entity.motherBoardFormFactor.length,entity.motherBoardFormFactor.width),
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
    fun findCase(option:String,value:String): Case {
        var entity = CaseEntity(Case())
        if(option=="name")
            entity = caseRepo.findByName(value).orElseThrow { CusComException(CusComErrorCode.CaseNotFound) }
        else if(option=="id")
            entity = caseRepo.findById(value.toLong()).orElseThrow { CusComException(CusComErrorCode.CaseNotFound) }

        return Case(
            entity.id,
            entity.name,
            entity.manufacturer,
            entity.caseType,
            MotherBoardFormFactor(entity.motherBoardFormFactor.name,entity.motherBoardFormFactor.length,entity.motherBoardFormFactor.width),
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
    fun updateCase(case: Case,beforeId:Long){
        val temp=caseRepo.findById(beforeId).orElseThrow { CusComException(CusComErrorCode.CaseNotFound) }
        temp.update(case)
    }

    @Transactional
    fun deleteCase(id:Long){
        return caseRepo.deleteById(id)
    }


    @Transactional(readOnly = true)
    fun getCpuCoolerList(): List<CPUCooler> {
        val cpuCoolerList:List<CPUCooler> = cpuCoolerRepo.findAll()
            .map{
                entity: CPUCoolerEntity -> CPUCooler(
                    entity.id,
                    entity.name,
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
    fun findCpuCooler(option:String,value:String): CPUCooler {
        var entity = CPUCoolerEntity(CPUCooler())
        if(option=="name")
            cpuCoolerRepo.findByName(value).orElseThrow { CusComException(CusComErrorCode.CPUCoolerNotFound) }
        else if(option=="id")
            cpuCoolerRepo.findById(value.toLong()).orElseThrow { CusComException(CusComErrorCode.CPUCoolerNotFound) }

        return CPUCooler(
            entity.id,
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
    fun updateCPUCooler(cpuCooler: CPUCooler,beforeId: Long){
        var temp=cpuCoolerRepo.findById(beforeId).orElseThrow { CusComException(CusComErrorCode.CPUCoolerNotFound) }
        temp.update(cpuCooler)
    }

    @Transactional
    fun deleteCPUCooler(id:Long){
        return cpuCoolerRepo.deleteById(id)
    }


    @Transactional(readOnly = true)
    fun getCPUList(): List<CPU> {
        val cpuList:List<CPU> = cpuRepo.findAll()
            .map{
                entity: CPUEntity -> CPU(
                    entity.id,
                    entity.name,
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
    fun findCpu(option:String,value:String): CPU {
        var entity = CPUEntity(CPU())
        if(option=="name")
            entity = cpuRepo.findByName(value).orElseThrow { CusComException(CusComErrorCode.CPUNotFound) }
        else if(option=="id")
            entity = cpuRepo.findById(value.toLong()).orElseThrow { CusComException(CusComErrorCode.CPUNotFound) }
        return CPU(
            entity.id,
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
    fun updateCPU(cpu: CPU,beforeId:Long){
        var temp=cpuRepo.findById(beforeId).orElseThrow { CusComException(CusComErrorCode.CPUNotFound) }
        temp.update(cpu)
    }

    @Transactional
    fun deleteCPU(id:Long){
        return cpuRepo.deleteById(id)
    }


    @Transactional(readOnly = true)
    fun getDataStorageList(): List<DataStorage> {
        val dataStorageList:List<DataStorage> = dataStorageRepo.findAll()
            .map{
                entity: DataStorageEntity -> DataStorage(
                    entity.id,
                    entity.name,
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
    fun findDataStorage(option:String,value:String): DataStorage {
        var entity = DataStorageEntity(DataStorage())
        if(option=="name")
            entity = dataStorageRepo.findByName(value).orElseThrow { CusComException(CusComErrorCode.DataStorageNotFound) }
        if(option=="id")
            entity = dataStorageRepo.findById(value.toLong()).orElseThrow { CusComException(CusComErrorCode.DataStorageNotFound) }

        return DataStorage(
            entity.id,
            entity.name,
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
    fun updateDataStorage(dataStorage: DataStorage,beforeId: Long){
        var temp=dataStorageRepo.findById(beforeId).orElseThrow { CusComException(CusComErrorCode.DataStorageNotFound) }
        temp.update(dataStorage)
    }

    @Transactional
    fun deleteDataStorage(id: Long){
        return dataStorageRepo.deleteById(id)
    }


    @Transactional(readOnly = true)
    fun getGraphicsCardList(): List<GraphicsCard> {
        val graphicsCardList:List<GraphicsCard> = graphicsCardRepo.findAll()
            .map{
                entity: GraphicsCardEntity -> GraphicsCard(
                    entity.id,
                    entity.name,
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
    fun findGraphicsCard(option:String,value:String): GraphicsCard {
        var entity = GraphicsCardEntity(GraphicsCard())
        if(option=="name")
            entity = graphicsCardRepo.findByName(value).orElseThrow { CusComException(CusComErrorCode.GraphicsCardNotFound) }
        else if(option=="id")
            entity = graphicsCardRepo.findById(value.toLong()).orElseThrow { CusComException(CusComErrorCode.GraphicsCardNotFound) }
        return GraphicsCard(
            entity.id,
            entity.name,
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
    fun updateGraphicsCard(graphicsCard: GraphicsCard,beforeId:Long){
        var temp=graphicsCardRepo.findById(beforeId).orElseThrow { CusComException(CusComErrorCode.GraphicsCardNotFound) }
        temp.update(graphicsCard)
    }

    @Transactional
    fun deleteGraphicsCard(id:Long){
        return graphicsCardRepo.deleteById(id)
    }


    @Transactional(readOnly = true)
    fun getMemoryList(): List<Memory> {
        val memoryList:List<Memory> = memoryRepo.findAll()
            .map{
                entity: MemoryEntity -> Memory(
                    entity.id,
                    entity.name,
                    entity.imageUrl,
                    entity.manufacturer,
                    entity.type,
                    entity.capacity,
                    entity.height) }
        return memoryList
    }

    @Transactional
    fun findMemory(option:String,value:String): Memory {
        var entity = MemoryEntity(Memory())
        if(option=="name")
            entity = memoryRepo.findByName(value).orElseThrow { CusComException(CusComErrorCode.MemoryNotFound) }
        else if(option=="id")
            entity = memoryRepo.findById(value.toLong()).orElseThrow { CusComException(CusComErrorCode.MemoryNotFound) }
        return Memory(
            entity.id,
            entity.name,
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
    fun updateMemory(memory: Memory,beforeId:Long){
        var temp=memoryRepo.findById(beforeId).orElseThrow { CusComException(CusComErrorCode.MemoryNotFound) }
        temp.update(memory)
    }

    @Transactional
    fun deleteMemory(id:Long){
        return memoryRepo.deleteById(id)
    }


    @Transactional(readOnly = true)
    fun getMotherBoardList(): List<MotherBoard> {
        val motherBoardList:List<MotherBoard> = motherBoardRepo.findAll()
            .map{
                entity: MotherBoardEntity -> MotherBoard(
                    entity.id,
                    entity.name,
                    entity.imageUrl,
                    entity.manufacturer,
                    entity.cpuType,
                    entity.socket,
                    entity.chipset,
                    MotherBoardFormFactor(entity.motherBoardFormFactor.name,entity.motherBoardFormFactor.length,entity.motherBoardFormFactor.width),
                    entity.memoryType,
                    entity.memorySlot,
                    entity.ssdM2Slot,
                    entity.ssdSATASlot) }
        return motherBoardList
    }

    @Transactional
    fun findMotherBoard(option:String,value:String): MotherBoard {
        var entity = MotherBoardEntity(MotherBoard())
        if(option=="name")
            entity = motherBoardRepo.findByName(value).orElseThrow { CusComException(CusComErrorCode.MotherBoardNotFound) }
        else if(option=="id")
            entity = motherBoardRepo.findById(value.toLong()).orElseThrow { CusComException(CusComErrorCode.MotherBoardNotFound) }
        return MotherBoard(
            entity.id,
            entity.name,
            entity.imageUrl,
            entity.manufacturer,
            entity.cpuType,
            entity.socket,
            entity.chipset,
            MotherBoardFormFactor(entity.motherBoardFormFactor.name,entity.motherBoardFormFactor.length,entity.motherBoardFormFactor.width),
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
    fun updateMotherBoard(motherBoard: MotherBoard,beforeId:Long){
        var temp=motherBoardRepo.findById(beforeId).orElseThrow { CusComException(CusComErrorCode.MotherBoardNotFound) }
        temp.update(motherBoard)
    }

    @Transactional
    fun deleteMotherBoard(id:Long){
        return motherBoardRepo.deleteById(id)
    }


    @Transactional(readOnly = true)
    fun getPowerSupplyList(): List<PowerSupply> {
        val powerSupplyList:List<PowerSupply> = powerSupplyRepo.findAll()
            .map{
                entity: PowerSupplyEntity -> PowerSupply(
                    entity.id,
                    entity.name,
                    entity.imageUrl,
                    entity.manufacturer,
                    entity.power,
                    entity.efficiency,
                    entity.modular,
                    entity.length) }
        return powerSupplyList
    }

    @Transactional
    fun findPowerSupply(option:String,value:String): PowerSupply {
        var entity = PowerSupplyEntity(PowerSupply())
        if(option=="name")
            entity = powerSupplyRepo.findByName(value).orElseThrow { CusComException(CusComErrorCode.PowerSupplyNotFound) }
        else if(option=="id")
            entity = powerSupplyRepo.findById(value.toLong()).orElseThrow { CusComException(CusComErrorCode.PowerSupplyNotFound) }
        return PowerSupply(
            entity.id,
            entity.name,
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
    fun updatePowerSupply(powerSupply: PowerSupply,beforeId:Long){
        var temp=powerSupplyRepo.findById(beforeId).orElseThrow { CusComException(CusComErrorCode.PowerSupplyNotFound) }
        temp.update(powerSupply)
    }

    @Transactional
    fun deletePowerSupply(id:Long){
        return powerSupplyRepo.deleteById(id)
    }

    fun findMotherBoardFormFactor(name: String): MotherBoardFormFactor {
        val entity=motherBoardFormFactorRepo.findById(name).orElseThrow { CusComException(CusComErrorCode.FormFactorNotFound) }
        return MotherBoardFormFactor(entity.name,entity.length,entity.width)
    }

    fun isContainSocket(chipset:String,socket:String): Boolean {
        val find=cpuSocketRepository.findById(chipset).get()
        return find.name==socket
    }
}