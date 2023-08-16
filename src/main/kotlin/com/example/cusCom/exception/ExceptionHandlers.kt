package com.example.cusCom.exception

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandlers {

    private val logger:Logger=LoggerFactory.getLogger(EstimateException::class.java)

    @ExceptionHandler(EstimateException::class)
    fun estimateBadRequest(e: EstimateException): ResponseEntity<String> {
        val estimateInfo=e.getErrorCode()
        logger.error("estimateBadRequest",estimateInfo.getCode())
        return ResponseEntity(estimateInfo.getMessage(),HttpStatus.valueOf(estimateInfo.getStatus()))
    }

}