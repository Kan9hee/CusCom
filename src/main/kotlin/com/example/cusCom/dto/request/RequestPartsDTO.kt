package com.example.cusCom.dto.request

import org.springframework.web.multipart.MultipartFile

data class RequestPartsDTO(val partsType:String,
                           val requestJSON:String,
                           val partsImage: MultipartFile?,
                           val beforePartsName:String?)