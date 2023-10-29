package com.example.cusCom.provideContent.entity.mongoDB

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "shareplace-posts")
class SharePlacePostEntity(val _id: ObjectId,
                           val estimateID:String,
                           var title:String,
                           var tags:Array<String>,
                           viewCountValue:Long,
                           likeCountValue:Long) {

    var viewCount:Long=viewCountValue
        protected set
    var likeCount:Long=likeCountValue
        protected set

    fun setPostData(title:String,content:String,tags:Array<String>){
        this.title=title
        this.tags=tags
    }
}