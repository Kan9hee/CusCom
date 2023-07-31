package com.example.cusCom.userEstimate.entity

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "shareplace-comments")
class CommentEntity(val _id: ObjectId,
                    val postID:String,
                    val userName:String,
                    comment:String) {

    var content:String=comment
        protected set
}