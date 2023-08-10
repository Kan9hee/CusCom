package com.example.cusCom.userEstimate.dto

data class SharePlacePost(var _id:String,
                          var estimateID:String,
                          var title:String,
                          var content:String,
                          var tags:Array<String>,
                          var viewCount:Long,
                          var likeCount:Long){
}