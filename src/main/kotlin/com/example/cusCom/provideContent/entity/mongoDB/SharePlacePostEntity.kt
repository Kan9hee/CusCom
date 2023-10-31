package com.example.cusCom.provideContent.entity.mongoDB

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "shareplace-posts")
class SharePlacePostEntity(val _id: ObjectId,
                           val estimateID:String,
                           var title:String,
                           val userName:String,
                           var tags:Array<String>,
                           viewCount:Long,
                           likeCount:Long) {

    var viewCount:Long=viewCount
        protected set
    var likeCount:Long=likeCount
        protected set

    fun setPostData(title:String,content:String,tags:Array<String>){
        this.title=title
        this.tags=tags
    }
}