package com.example.cusCom.userEstimate.entity

import com.example.cusCom.userEstimate.dto.SharePlacePost
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "shareplace-posts")
class SharePlacePostEntity(sharePlacePost: SharePlacePost) {
    var estimateID:String=sharePlacePost.estimateID
    var title:String=sharePlacePost.title
    var content:String=sharePlacePost.content
    var tags:Array<String> = sharePlacePost.tags
    var viewCount:Long=sharePlacePost.viewCount
    var likeCount:Long=sharePlacePost.likeCount
}