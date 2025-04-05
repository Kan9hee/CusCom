package com.example.cusCom.service

import com.example.cusCom.config.InnerStringsConfig
import com.example.cusCom.exception.CusComErrorCode
import com.example.cusCom.exception.CusComException
import com.example.cusCom.dto.parts.*
import com.example.cusCom.entity.mySQL.parts.*
import com.example.cusCom.repository.parts.*
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
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
                          private val powerSupplyRepo:PowerSupplyRepository,
                          private val innerStringsConfig: InnerStringsConfig) {

    @Transactional(readOnly = true)
    fun getCaseList(maxContent:Int, page:Int): List<CaseDTO> {
        val pageable = buildPageRequest(maxContent,page)
        val caseDTOLists = caseRepo.findAll(pageable)
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
        return caseDTOLists.toList()
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
            caseDTO.imageUrl?:innerStringsConfig.property.aws.defaultImageUrl
        )
        caseRepo.save(newCaseData)
    }

    @Transactional
    fun updateCase(caseDTO: CaseDTO,beforePartName:String?){
        if(beforePartName==null)
            throw CusComException(CusComErrorCode.CaseNotFound)
        val temp = caseRepo.findByName(beforePartName)
            ?: throw CusComException(CusComErrorCode.CaseNotFound)
        temp.update(caseDTO)
    }

    @Transactional
    fun deleteCase(name:String){
        return caseRepo.deleteByName(name)
    }


    @Transactional(readOnly = true)
    fun getCpuCoolerList(maxContent:Int, page:Int): List<CpuCoolerDTO> {
        val pageable = buildPageRequest(maxContent,page)
        val cpuCoolerDTOLists = cpuCoolerRepo.findAll(pageable)
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
        return cpuCoolerDTOLists.toList()
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
            cpuCoolerDTO.imageUrl?:innerStringsConfig.property.aws.defaultImageUrl,
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
    fun updateCPUCooler(cpuCoolerDTO: CpuCoolerDTO,beforePartName:String?){
        if(beforePartName==null)
            throw CusComException(CusComErrorCode.CaseNotFound)
        val temp = cpuCoolerRepo.findByName(beforePartName)
            ?: throw CusComException(CusComErrorCode.CPUCoolerNotFound)
        temp.update(cpuCoolerDTO)
    }

    @Transactional
    fun deleteCPUCooler(name:String){
        return cpuCoolerRepo.deleteByName(name)
    }


    @Transactional(readOnly = true)
    fun getCPUList(maxContent:Int, page:Int): List<CpuDTO> {
        val pageable = buildPageRequest(maxContent,page)
        val cpuDTOLists = cpuRepo.findAll(pageable)
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
        return cpuDTOLists.toList()
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
            cpuDTO.imageUrl?:innerStringsConfig.property.aws.defaultImageUrl,
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
    fun updateCPU(cpuDTO: CpuDTO,beforePartName:String?){
        if(beforePartName==null)
            throw CusComException(CusComErrorCode.CaseNotFound)
        val temp = cpuRepo.findByName(beforePartName)
            ?: throw CusComException(CusComErrorCode.CPUNotFound)
        temp.update(cpuDTO)
    }

    @Transactional
    fun deleteCPU(name:String){
        return cpuRepo.deleteByName(name)
    }


    @Transactional(readOnly = true)
    fun getDataStorageList(maxContent:Int, page:Int): List<DataStorageDTO> {
        val pageable = buildPageRequest(maxContent,page)
        val dataStorageDTOLists = dataStorageRepo.findAll(pageable)
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
        return dataStorageDTOLists.toList()
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
            dataStorageDTO.imageUrl?:innerStringsConfig.property.aws.defaultImageUrl,
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
    fun updateDataStorage(dataStorageDTO: DataStorageDTO,beforePartName:String?){
        if(beforePartName==null)
            throw CusComException(CusComErrorCode.CaseNotFound)
        val temp = dataStorageRepo.findByName(beforePartName)
            ?: throw CusComException(CusComErrorCode.DataStorageNotFound)
        temp.update(dataStorageDTO)
    }

    @Transactional
    fun deleteDataStorage(name:String){
        return dataStorageRepo.deleteByName(name)
    }


    @Transactional(readOnly = true)
    fun getGraphicsCardList(maxContent:Int, page:Int): List<GraphicsCardDTO> {
        val pageable = buildPageRequest(maxContent,page)
        val graphicsCardDTOLists = graphicsCardRepo.findAll(pageable)
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
        return graphicsCardDTOLists.toList()
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
            graphicsCardDTO.imageUrl?:innerStringsConfig.property.aws.defaultImageUrl,
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
    fun updateGraphicsCard(graphicsCardDTO: GraphicsCardDTO,beforePartName:String?){
        if(beforePartName==null)
            throw CusComException(CusComErrorCode.CaseNotFound)
        val temp = graphicsCardRepo.findByName(beforePartName)
            ?: throw CusComException(CusComErrorCode.GraphicsCardNotFound)
        temp.update(graphicsCardDTO)
    }

    @Transactional
    fun deleteGraphicsCard(name:String){
        return graphicsCardRepo.deleteByName(name)
    }


    @Transactional(readOnly = true)
    fun getMemoryList(maxContent:Int, page:Int): List<MemoryDTO> {
        val pageable = buildPageRequest(maxContent,page)
        val memoryDTOLists = memoryRepo.findAll(pageable)
            .map{
                entity: MemoryEntity -> MemoryDTO(
                    entity.name,
                    entity.imageUrl,
                    entity.manufacturer,
                    entity.type,
                    entity.capacity,
                    entity.height) }
        return memoryDTOLists.toList()
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
            memoryDTO.imageUrl?:innerStringsConfig.property.aws.defaultImageUrl,
            memoryDTO.manufacturer,
            memoryDTO.type,
            memoryDTO.capacity,
            memoryDTO.height
        )
        memoryRepo.save(newMemoryData)
    }

    @Transactional
    fun updateMemory(memoryDTO: MemoryDTO,beforePartName:String?){
        if(beforePartName==null)
            throw CusComException(CusComErrorCode.CaseNotFound)
        val temp = memoryRepo.findByName(beforePartName)
            ?: throw CusComException(CusComErrorCode.MemoryNotFound)
        temp.update(memoryDTO)
    }

    @Transactional
    fun deleteMemory(name:String){
        return memoryRepo.deleteByName(name)
    }


    @Transactional(readOnly = true)
    fun getMotherBoardList(maxContent:Int, page:Int): List<MotherBoardDTO> {
        val pageable = buildPageRequest(maxContent,page)
        val motherBoardDTOLists = motherBoardRepo.findAll(pageable)
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
        return motherBoardDTOLists.toList()
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
            motherBoardDTO.imageUrl?:innerStringsConfig.property.aws.defaultImageUrl,
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
    fun updateMotherBoard(motherBoardDTO: MotherBoardDTO,beforePartName:String?){
        if(beforePartName==null)
            throw CusComException(CusComErrorCode.CaseNotFound)
        val temp = motherBoardRepo.findByName(beforePartName)
            ?: throw CusComException(CusComErrorCode.MotherBoardNotFound)
        temp.update(motherBoardDTO)
    }

    @Transactional
    fun deleteMotherBoard(name:String){
        return motherBoardRepo.deleteByName(name)
    }


    @Transactional(readOnly = true)
    fun getPowerSupplyList(maxContent:Int, page:Int): List<PowerSupplyDTO> {
        val pageable = buildPageRequest(maxContent,page)
        val powerSupplyDTOLists = powerSupplyRepo.findAll(pageable)
            .map{
                entity: PowerSupplyEntity -> PowerSupplyDTO(
                    entity.name,
                    entity.imageUrl,
                    entity.manufacturer,
                    entity.power,
                    entity.efficiency,
                    entity.modular,
                    entity.length) }
        return powerSupplyDTOLists.toList()
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
            powerSupplyDTO.imageUrl?:innerStringsConfig.property.aws.defaultImageUrl,
            powerSupplyDTO.manufacturer,
            powerSupplyDTO.power,
            powerSupplyDTO.efficiency,
            powerSupplyDTO.modular,
            powerSupplyDTO.length
        )
        powerSupplyRepo.save(newPowerSupplyData)
    }

    @Transactional
    fun updatePowerSupply(powerSupplyDTO: PowerSupplyDTO,beforePartName:String?){
        if(beforePartName==null)
            throw CusComException(CusComErrorCode.CaseNotFound)
        val temp = powerSupplyRepo.findByName(beforePartName)
            ?: throw CusComException(CusComErrorCode.PowerSupplyNotFound)
        temp.update(powerSupplyDTO)
    }

    @Transactional
    fun deletePowerSupply(name:String){
        return powerSupplyRepo.deleteByName(name)
    }

    private fun buildPageRequest(currentPage:Int,maxContent:Int): PageRequest {
        return PageRequest
            .of(currentPage - 1,
                maxContent,
                Sort.by(Sort.Direction.ASC, innerStringsConfig.property.findOption.name)
            )
    }
}