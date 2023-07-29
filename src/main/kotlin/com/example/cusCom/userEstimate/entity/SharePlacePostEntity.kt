package com.example.cusCom.userEstimate.entity

import com.example.cusCom.userEstimate.dto.SharePlacePost
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "shareplace-posts")
class SharePlacePostEntity(var _id: ObjectId,
                           var estimateID:String,
                           var title:String,
                           var content:String,
                           var tags:Array<String>,
                           var viewCount:Long,
                           var likeCount:Long) {
    fun convert(sharePlacePost: SharePlacePost){
        this.estimateID=sharePlacePost.estimateID
        this.title=sharePlacePost.title
        this.content=sharePlacePost.content
        this.tags=sharePlacePost.tags
        this.viewCount=sharePlacePost.viewCount
        this.likeCount=sharePlacePost.likeCount
    }
}