package com.example.cusCom.dto.request

data class SaveSharePlacePostDTO(var estimateID:String,
                                 var title:String,
                                 val userName:String,
                                 val thumbnail:String,
                                 var tags:List<String>)
