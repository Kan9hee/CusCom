package com.example.cusCom.exception

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandlers {

    private val logger:Logger=LoggerFactory.getLogger(CusComException::class.java)

    @ExceptionHandler(CusComException::class)
    fun estimateBadRequest(e: CusComException): ResponseEntity<Any> {
        val estimateInfo=e.getErrorCode()
        val responseMap = mapOf(
            "message" to estimateInfo.getMessage(),
            "status" to estimateInfo.getStatus()
        )
        logger.error(estimateInfo.getCode())

        return ResponseEntity
            .status(estimateInfo.getStatus())
            .body(responseMap)
    }
}