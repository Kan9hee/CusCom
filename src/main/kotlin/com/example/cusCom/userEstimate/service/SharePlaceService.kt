package com.example.cusCom.userEstimate.service

import com.example.cusCom.userEstimate.dto.Comment
import com.example.cusCom.userEstimate.dto.SharePlacePost
import com.example.cusCom.userEstimate.entity.CommentEntity
import com.example.cusCom.userEstimate.entity.SharePlacePostEntity
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SharePlaceService(private val mongoTemplate: MongoTemplate) {

    @Transactional
    fun uploadPost(sharePlacePost: SharePlacePost){
        mongoTemplate.insert(SharePlacePostEntity(sharePlacePost))
    }

    @Transactional
    fun deletePost(postID:String){
        val query= Query(Criteria.where("_id").`is`(ObjectId(postID)))
        mongoTemplate.remove(query,SharePlacePostEntity::class.java)
    }

    @Transactional
    fun loadPostList(): List<SharePlacePost> {
        return mongoTemplate.findAll(SharePlacePostEntity::class.java).map {
            entity:SharePlacePostEntity->SharePlacePost(
                entity.estimateID,
                entity.content,
                entity.tags,
                entity.viewCount,
                entity.likeCount
            )
        }
    }

    @Transactional
    fun writeComment(comment: Comment){
        mongoTemplate.insert(CommentEntity(comment))
    }

    @Transactional
    fun deleteComment(commentID:String){
        val query= Query(Criteria.where("_id").`is`(ObjectId(commentID)))
        mongoTemplate.remove(query,CommentEntity::class.java)
    }

    @Transactional
    fun loadCommentList(postID:String): List<Comment> {
        val query= Query(Criteria.where("postID").`is`(ObjectId(postID)))
        return mongoTemplate.find(query,CommentEntity::class.java).map {
            entity:CommentEntity->Comment(
                entity.postID,
                entity.userName,
                entity.content
            )
        }
    }
}