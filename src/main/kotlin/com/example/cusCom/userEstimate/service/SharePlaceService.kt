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
        mongoTemplate.insert(SharePlacePostEntity(
            ObjectId(),
            sharePlacePost.estimateID,
            sharePlacePost.title,
            sharePlacePost.content,
            sharePlacePost.tags,
            sharePlacePost.viewCount,
            sharePlacePost.likeCount)
        )
    }

    @Transactional
    fun deletePost(option:String,value:String){
        val query= Query(Criteria.where(option).`is`(ObjectId(value)))
        deleteComment("postID",value)
        mongoTemplate.remove(query,SharePlacePostEntity::class.java)
    }

    @Transactional
    fun getPost(option:String,value:String): SharePlacePost? {
        val query= Query(Criteria.where(option).`is`(ObjectId(value)))
        val entity = mongoTemplate.findOne(query,SharePlacePostEntity::class.java)
        return entity?.let {
            SharePlacePost(
                it._id.toHexString(),
                it.estimateID,
                it.title,
                it.content,
                it.tags,
                it.viewCount,
                it.likeCount)
        }
    }

    @Transactional
    fun getPostList(): List<SharePlacePost> {
        return mongoTemplate.findAll(SharePlacePostEntity::class.java).map {
            entity:SharePlacePostEntity->SharePlacePost(
                entity._id.toHexString(),
                entity.estimateID,
                entity.title,
                entity.content,
                entity.tags,
                entity.viewCount,
                entity.likeCount
            )
        }
    }

    @Transactional
    fun uploadComment(comment: Comment){
        mongoTemplate.insert(CommentEntity(
            ObjectId(),
            comment.postID,
            comment.userName,
            comment.content)
        )
    }

    @Transactional
    fun deleteComment(option:String,value:String){
        val query= Query(Criteria.where(option).`is`(ObjectId(value)))
        mongoTemplate.remove(query,CommentEntity::class.java)
    }

    @Transactional
    fun getCommentList(option:String,value:String): List<Comment> {
        val query= Query(Criteria.where(option).`is`(value))
        return mongoTemplate.find(query,CommentEntity::class.java).map {
            entity:CommentEntity->Comment(
                entity._id.toHexString(),
                entity.postID,
                entity.userName,
                entity.content
            )
        }
    }
}