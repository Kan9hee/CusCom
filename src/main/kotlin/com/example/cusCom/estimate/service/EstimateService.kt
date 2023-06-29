package com.example.cusCom.estimate.service

import com.example.cusCom.estimate.dto.Estimate
import com.example.cusCom.estimate.entity.MotherBoardFormFactorEntity
import com.example.cusCom.estimate.repository.EstimateRepository
import com.example.cusCom.estimate.repository.MotherBoardFormFactorRepository
import org.springframework.transaction.annotation.Transactional
import org.springframework.stereotype.Service

@Service
class EstimateService(private val estimateRepo:EstimateRepository,
                      private val motherBoardRepo:MotherBoardFormFactorRepository) {

    @Transactional(readOnly=true)
    fun getMotherboardSize(formFactor:String){
        val formFactor:MotherBoardFormFactorEntity=motherBoardRepo.findById(formFactor).get()
    }

    @Transactional
    fun saveUserEstimate(information:Estimate){
        estimateRepo.save(information.toEstimateEntity())
    }
}