package com.example.cusCom.userEstimate.entity

import com.example.cusCom.userEstimate.dto.Comment
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "shareplace-comments")
class CommentEntity(var _id: ObjectId,
                    var postID:String,
                    var userName:String,
                    var content:String) {
    fun convert(comment: Comment) {
        var postID:String=comment.postID
        var userName:String=comment.userName
        var content:String=comment.content
    }
}