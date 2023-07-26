package com.example.cusCom.userEstimate.entity

import com.example.cusCom.userEstimate.dto.Comment
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "shareplace-comments")
class CommentEntity(comment: Comment) {
    var postID:String=comment.postID
    var userName:String=comment.userName
    var content:String=comment.content
}