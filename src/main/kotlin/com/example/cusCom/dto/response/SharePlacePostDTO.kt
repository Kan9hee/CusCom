package com.example.cusCom.dto.response

data class SharePlacePostDTO(var _id:String,
                             var estimateID:String,
                             var title:String,
                             val userName:String,
                             val thumbnail:String,
                             var tags:List<String>,
                             var viewCount:Long,
                             var likeCount:Long,
                             var commentCount:Long)