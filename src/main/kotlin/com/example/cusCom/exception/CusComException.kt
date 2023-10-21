package com.example.cusCom.exception

class CusComException(private val errorCode: CusComErrorCode)
    :RuntimeException(errorCode.getMessage())  {

    fun getErrorCode(): CusComErrorCode { return errorCode }
}
