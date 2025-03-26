package com.example.cusCom.dto.request

import com.example.cusCom.dto.parts.*

data class EstimateAnalyzeDTO(val estimateId:String?,
                              val cpu: CpuDTO,
                              val motherBoard: MotherBoardDTO,
                              val memory: MemoryDTO,
                              val dataStorage: DataStorageDTO,
                              val graphicsCard: GraphicsCardDTO,
                              val cpuCooler: CpuCoolerDTO,
                              val powerSupply: PowerSupplyDTO,
                              val desktopCase: CaseDTO)
