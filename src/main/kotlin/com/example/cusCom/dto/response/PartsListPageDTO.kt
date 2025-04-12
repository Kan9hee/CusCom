package com.example.cusCom.dto.response

data class PartsListPageDTO<T>(val data:List<T>,
                               val totalPages:Int)
