package com.example.cusCom.service

import com.example.cusCom.exception.CusComErrorCode
import com.example.cusCom.exception.CusComException
import com.example.cusCom.dto.parts.*
import com.example.cusCom.entity.mySQL.parts.*
import com.example.cusCom.repository.parts.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DesktopPartsService(private val caseRepo: CaseRepository,
                          private val cpuCoolerRepo: CPUCoolerRepository,
                          private val cpuRepo: CPURepository,
                          private val dataStorageRepo: DataStorageRepository,
                          private val graphicsCardRepo: GraphicsCardRepository,
                          private val memoryRepo:MemoryRepository,
                          private val motherBoardRepo: MotherBoardRepository,
                          private val powerSupplyRepo:PowerSupplyRepository) {

    @Transactional(readOnly = true)
    fun getCaseList(): List<CaseDTO> {
        val caseDTOLists:List<CaseDTO> = caseRepo.findAll()
            .map{
                entity: CaseEntity -> CaseDTO(
                    entity.name,
                    entity.manufacturer,
                    entity.caseType,
                    entity.motherBoardFormFactor,
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
        return caseDTOLists
    }

    @Transactional
    fun findCase(value:String): CaseDTO {
        val entity = caseRepo.findByName(value)
            ?: throw CusComException(CusComErrorCode.CaseNotFound)
        return CaseDTO(
            entity.name,
            entity.manufacturer,
            entity.caseType,
            entity.motherBoardFormFactor,
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
    fun createCase(caseDTO: CaseDTO){
        val newCaseData = CaseEntity(
            caseDTO.name,
            caseDTO.manufacturer,
            caseDTO.caseType,
            caseDTO.motherBoardFormFactor,
            caseDTO.maxCoolingFan,
            caseDTO.builtInCoolingFan,
            caseDTO.height,
            caseDTO.length,
            caseDTO.width,
            caseDTO.powerLength,
            caseDTO.cpuCoolerHeight,
            caseDTO.graphicsCardLength,
            caseDTO.imageUrl
        )
        caseRepo.save(newCaseData)
    }

    @Transactional
    fun updateCase(caseDTO: CaseDTO,beforePartName:String){
        val temp = caseRepo.findByName(beforePartName)
            ?: throw CusComException(CusComErrorCode.CaseNotFound)
        temp.update(caseDTO)
    }

    @Transactional
    fun deleteCase(name:String){
        return caseRepo.deleteByName(name)
    }


    @Transactional(readOnly = true)
    fun getCpuCoolerList(): List<CpuCoolerDTO> {
        val cpuCoolerDTOLists:List<CpuCoolerDTO> = cpuCoolerRepo.findAll()
            .map{
                entity: CPUCoolerEntity -> CpuCoolerDTO(
                    entity.name,
                    entity.imageUrl,
                    entity.manufacturer,
                    entity.coolingType,
                    entity.coolerForm,
                    entity.height,
                    entity.length,
                    entity.width,
                    entity.tdp) }
        return cpuCoolerDTOLists
    }

    @Transactional
    fun findCpuCooler(value:String): CpuCoolerDTO {
        val entity = cpuCoolerRepo.findByName(value)
            ?: throw CusComException(CusComErrorCode.CPUCoolerNotFound)

        return CpuCoolerDTO(
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
    fun createCPUCooler(cpuCoolerDTO: CpuCoolerDTO){
        val newCpuCoolerData = CPUCoolerEntity(
            cpuCoolerDTO.name,
            cpuCoolerDTO.imageUrl,
            cpuCoolerDTO.manufacturer,
            cpuCoolerDTO.coolingType,
            cpuCoolerDTO.coolerForm,
            cpuCoolerDTO.height,
            cpuCoolerDTO.length,
            cpuCoolerDTO.width,
            cpuCoolerDTO.tdp
        )
        cpuCoolerRepo.save(newCpuCoolerData)
    }

    @Transactional
    fun updateCPUCooler(cpuCoolerDTO: CpuCoolerDTO,beforePartName:String){
        val temp = cpuCoolerRepo.findByName(beforePartName)
            ?: throw CusComException(CusComErrorCode.CPUCoolerNotFound)
        temp.update(cpuCoolerDTO)
    }

    @Transactional
    fun deleteCPUCooler(name:String){
        return cpuCoolerRepo.deleteByName(name)
    }


    @Transactional(readOnly = true)
    fun getCPUList(): List<CpuDTO> {
        val cpuDTOLists:List<CpuDTO> = cpuRepo.findAll()
            .map{
                entity: CPUEntity -> CpuDTO(
                    entity.name,
                    entity.imageUrl,
                    entity.manufacturer,
                    entity.socket,
                    entity.memoryType,
                    entity.core,
                    entity.thread,
                    entity.isBuiltInGraphics,
                    entity.builtInGraphicName,
                    entity.tdp) }
        return cpuDTOLists
    }

    @Transactional
    fun findCpu(value:String): CpuDTO {
        val entity = cpuRepo.findByName(value)
            ?: throw CusComException(CusComErrorCode.CPUNotFound)
        return CpuDTO(
            entity.name,
            entity.imageUrl,
            entity.manufacturer,
            entity.socket,
            entity.memoryType,
            entity.core,
            entity.thread,
            entity.isBuiltInGraphics,
            entity.builtInGraphicName,
            entity.tdp
        )
    }

    @Transactional
    fun createCPU(cpuDTO: CpuDTO){
        val newCpuData = CPUEntity(
            cpuDTO.name,
            cpuDTO.imageUrl,
            cpuDTO.manufacturer,
            cpuDTO.socket,
            cpuDTO.memoryType,
            cpuDTO.core,
            cpuDTO.thread,
            cpuDTO.isBuiltInGraphics,
            cpuDTO.builtInGraphicName,
            cpuDTO.tdp
        )
        cpuRepo.save(newCpuData)
    }

    @Transactional
    fun updateCPU(cpuDTO: CpuDTO,beforePartName:String){
        val temp = cpuRepo.findByName(beforePartName)
            ?: throw CusComException(CusComErrorCode.CPUNotFound)
        temp.update(cpuDTO)
    }

    @Transactional
    fun deleteCPU(name:String){
        return cpuRepo.deleteByName(name)
    }


    @Transactional(readOnly = true)
    fun getDataStorageList(): List<DataStorageDTO> {
        val dataStorageDTOLists:List<DataStorageDTO> = dataStorageRepo.findAll()
            .map{
                entity: DataStorageEntity -> DataStorageDTO(
                    entity.name,
                    entity.imageUrl,
                    entity.manufacturer,
                    entity.storageInterface,
                    entity.formFactor,
                    entity.capacity,
                    entity.readSpeed,
                    entity.writeSpeed) }
        return dataStorageDTOLists
    }

    @Transactional
    fun findDataStorage(value:String): DataStorageDTO {
        val entity = dataStorageRepo.findByName(value)
            ?: throw CusComException(CusComErrorCode.DataStorageNotFound)
        return DataStorageDTO(
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
    fun createDataStorage(dataStorageDTO: DataStorageDTO) {
        val newDataStorageData = DataStorageEntity(
            dataStorageDTO.name,
            dataStorageDTO.imageUrl,
            dataStorageDTO.manufacturer,
            dataStorageDTO.storageInterface,
            dataStorageDTO.formFactor,
            dataStorageDTO.capacity,
            dataStorageDTO.readSpeed,
            dataStorageDTO.writeSpeed
        )
        dataStorageRepo.save(newDataStorageData)
    }

    @Transactional
    fun updateDataStorage(dataStorageDTO: DataStorageDTO,beforePartName:String){
        val temp = dataStorageRepo.findByName(beforePartName)
            ?: throw CusComException(CusComErrorCode.DataStorageNotFound)
        temp.update(dataStorageDTO)
    }

    @Transactional
    fun deleteDataStorage(name:String){
        return dataStorageRepo.deleteByName(name)
    }


    @Transactional(readOnly = true)
    fun getGraphicsCardList(): List<GraphicsCardDTO> {
        val graphicsCardDTOLists:List<GraphicsCardDTO> = graphicsCardRepo.findAll()
            .map{
                entity: GraphicsCardEntity -> GraphicsCardDTO(
                    entity.name,
                    entity.imageUrl,
                    entity.manufacturer,
                    entity.chipsetManufacturer,
                    entity.gpuType,
                    entity.length,
                    entity.basicPower,
                    entity.maxPower,
                    entity.phase) }
        return graphicsCardDTOLists
    }

    @Transactional
    fun findGraphicsCard(value:String): GraphicsCardDTO {
        val entity = graphicsCardRepo.findByName(value)
            ?: throw CusComException(CusComErrorCode.GraphicsCardNotFound)
        return GraphicsCardDTO(
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
    fun createGraphicsCard(graphicsCardDTO: GraphicsCardDTO) {
        val newGraphicsCardData = GraphicsCardEntity(
            graphicsCardDTO.name,
            graphicsCardDTO.imageUrl,
            graphicsCardDTO.manufacturer,
            graphicsCardDTO.chipsetManufacturer,
            graphicsCardDTO.gpuType,
            graphicsCardDTO.length,
            graphicsCardDTO.basicPower,
            graphicsCardDTO.maxPower,
            graphicsCardDTO.phase
        )
        graphicsCardRepo.save(newGraphicsCardData)
    }

    @Transactional
    fun updateGraphicsCard(graphicsCardDTO: GraphicsCardDTO,beforePartName:String){
        val temp = graphicsCardRepo.findByName(beforePartName)
            ?: throw CusComException(CusComErrorCode.GraphicsCardNotFound)
        temp.update(graphicsCardDTO)
    }

    @Transactional
    fun deleteGraphicsCard(name:String){
        return graphicsCardRepo.deleteByName(name)
    }


    @Transactional(readOnly = true)
    fun getMemoryList(): List<MemoryDTO> {
        val memoryDTOLists:List<MemoryDTO> = memoryRepo.findAll()
            .map{
                entity: MemoryEntity -> MemoryDTO(
                    entity.name,
                    entity.imageUrl,
                    entity.manufacturer,
                    entity.type,
                    entity.capacity,
                    entity.height) }
        return memoryDTOLists
    }

    @Transactional
    fun findMemory(value:String): MemoryDTO {
        val entity = memoryRepo.findByName(value)
            ?: throw CusComException(CusComErrorCode.MemoryNotFound)
        return MemoryDTO(
            entity.name,
            entity.imageUrl,
            entity.manufacturer,
            entity.type,
            entity.capacity,
            entity.height)
    }

    @Transactional
    fun createMemory(memoryDTO: MemoryDTO){
        val newMemoryData = MemoryEntity(
            memoryDTO.name,
            memoryDTO.imageUrl,
            memoryDTO.manufacturer,
            memoryDTO.type,
            memoryDTO.capacity,
            memoryDTO.height
        )
        memoryRepo.save(newMemoryData)
    }

    @Transactional
    fun updateMemory(memoryDTO: MemoryDTO,beforePartName:String){
        val temp = memoryRepo.findByName(beforePartName)
            ?: throw CusComException(CusComErrorCode.MemoryNotFound)
        temp.update(memoryDTO)
    }

    @Transactional
    fun deleteMemory(name:String){
        return memoryRepo.deleteByName(name)
    }


    @Transactional(readOnly = true)
    fun getMotherBoardList(): List<MotherBoardDTO> {
        val motherBoardDTOLists:List<MotherBoardDTO> = motherBoardRepo.findAll()
            .map{
                entity: MotherBoardEntity -> MotherBoardDTO(
                    entity.name,
                    entity.imageUrl,
                    entity.manufacturer,
                    entity.cpuType,
                    entity.socket,
                    entity.chipset,
                    entity.motherBoardFormFactor,
                    entity.memoryType,
                    entity.memorySlot,
                    entity.ssdM2Slot,
                    entity.ssdSATASlot) }
        return motherBoardDTOLists
    }

    @Transactional
    fun findMotherBoard(value:String): MotherBoardDTO {
        val entity = motherBoardRepo.findByName(value)
            ?: throw CusComException(CusComErrorCode.MotherBoardNotFound)
        return MotherBoardDTO(
            entity.name,
            entity.imageUrl,
            entity.manufacturer,
            entity.cpuType,
            entity.socket,
            entity.chipset,
            entity.motherBoardFormFactor,
            entity.memoryType,
            entity.memorySlot,
            entity.ssdM2Slot,
            entity.ssdSATASlot)
    }

    @Transactional
    fun createMotherBoard(motherBoardDTO: MotherBoardDTO){
        val newMotherBoardData = MotherBoardEntity(
            motherBoardDTO.name,
            motherBoardDTO.imageUrl,
            motherBoardDTO.manufacturer,
            motherBoardDTO.cpuType,
            motherBoardDTO.socket,
            motherBoardDTO.chipset,
            motherBoardDTO.motherBoardFormFactor,
            motherBoardDTO.memoryType,
            motherBoardDTO.memorySlot,
            motherBoardDTO.ssdM2Slot,
            motherBoardDTO.ssdSATASlot
        )
        motherBoardRepo.save(newMotherBoardData)
    }

    @Transactional
    fun updateMotherBoard(motherBoardDTO: MotherBoardDTO,beforePartName:String){
        val temp = motherBoardRepo.findByName(beforePartName)
            ?: throw CusComException(CusComErrorCode.MotherBoardNotFound)
        temp.update(motherBoardDTO)
    }

    @Transactional
    fun deleteMotherBoard(name:String){
        return motherBoardRepo.deleteByName(name)
    }


    @Transactional(readOnly = true)
    fun getPowerSupplyList(): List<PowerSupplyDTO> {
        val powerSupplyDTOLists:List<PowerSupplyDTO> = powerSupplyRepo.findAll()
            .map{
                entity: PowerSupplyEntity -> PowerSupplyDTO(
                    entity.name,
                    entity.imageUrl,
                    entity.manufacturer,
                    entity.power,
                    entity.efficiency,
                    entity.modular,
                    entity.length) }
        return powerSupplyDTOLists
    }

    @Transactional
    fun findPowerSupply(value:String): PowerSupplyDTO {
        val entity = powerSupplyRepo.findByName(value)
            ?: throw CusComException(CusComErrorCode.PowerSupplyNotFound)
        return PowerSupplyDTO(
            entity.name,
            entity.imageUrl,
            entity.manufacturer,
            entity.power,
            entity.efficiency,
            entity.modular,
            entity.length)
    }

    @Transactional
    fun createPowerSupply(powerSupplyDTO: PowerSupplyDTO){
        val newPowerSupplyData = PowerSupplyEntity(
            powerSupplyDTO.name,
            powerSupplyDTO.imageUrl,
            powerSupplyDTO.manufacturer,
            powerSupplyDTO.power,
            powerSupplyDTO.efficiency,
            powerSupplyDTO.modular,
            powerSupplyDTO.length
        )
        powerSupplyRepo.save(newPowerSupplyData)
    }

    @Transactional
    fun updatePowerSupply(powerSupplyDTO: PowerSupplyDTO,beforePartName:String){
        val temp = powerSupplyRepo.findByName(beforePartName)
            ?: throw CusComException(CusComErrorCode.PowerSupplyNotFound)
        temp.update(powerSupplyDTO)
    }

    @Transactional
    fun deletePowerSupply(name:String){
        return powerSupplyRepo.deleteByName(name)
    }
}