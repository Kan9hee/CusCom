package com.example.cusCom.exception

class EstimateException(private val errorCode: EstimateErrorCode)
    :RuntimeException(errorCode.getMessage())  {

    fun getErrorCode(): EstimateErrorCode { return errorCode }
}
