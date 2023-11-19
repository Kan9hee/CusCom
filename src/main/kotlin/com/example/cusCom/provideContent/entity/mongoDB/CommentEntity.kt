package com.example.cusCom.provideContent.entity.mongoDB

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "\${dbString.mysql.collection.comment}")
class CommentEntity(val _id: ObjectId,
                    val postID:String,
                    val userName:String,
                    content:String) {

    var content:String=content
        protected set
}