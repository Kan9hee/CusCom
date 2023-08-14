package com.example.cusCom.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ExceptionHandlers {

    @ExceptionHandler(EstimateException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleFileUploadBadRequest(ex: EstimateException): String {
        return "견적사항에 이상 발생: ${ex.message}"
    }

}