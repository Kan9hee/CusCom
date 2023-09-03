package com.example.cusCom.provideContent.service

import com.example.cusCom.provideContent.dto.Comment
import com.example.cusCom.provideContent.dto.SharePlacePost
import com.example.cusCom.provideContent.entity.mongoDB.CommentEntity
import com.example.cusCom.provideContent.entity.mongoDB.SharePlacePostEntity
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SharePlaceService(private val mongoTemplate: MongoTemplate) {

    @Transactional
    fun uploadPost(sharePlacePost: SharePlacePost){
        mongoTemplate.insert(
            SharePlacePostEntity(
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
        mongoTemplate.remove(query, SharePlacePostEntity::class.java)
    }

    @Transactional
    fun loadPost(option:String,value:String): SharePlacePost? {
        val query= Query(Criteria.where(option).`is`(ObjectId(value)))
        val entity = mongoTemplate.findOne(query, SharePlacePostEntity::class.java)
        return entity?.let {
            increaseViewCount(it)
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
            entity: SharePlacePostEntity ->
            SharePlacePost(
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
    fun searchPost(option:String,value:String): List<SharePlacePost> {
        val query= Query(Criteria.where(option).`is`(ObjectId(value)))
        return mongoTemplate.find(query,SharePlacePostEntity::class.java).map {
                entity: SharePlacePostEntity ->
                SharePlacePost(
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
    private fun increaseViewCount(sharePlacePostEntity: SharePlacePostEntity){
        val query= Query(Criteria.where("_id").`is`(sharePlacePostEntity._id))
        val update= Update().inc("viewCount",sharePlacePostEntity.viewCount+1)
        mongoTemplate.updateFirst(query,update,"shareplace-posts")
    }

    @Transactional
    fun uploadComment(comment: Comment){
        mongoTemplate.insert(
            CommentEntity(
            ObjectId(),
            comment.postID,
            comment.userName,
            comment.content)
        )
    }

    @Transactional
    fun deleteComment(option:String,value:String){
        val query= Query(Criteria.where(option).`is`(ObjectId(value)))
        mongoTemplate.remove(query, CommentEntity::class.java)
    }

    @Transactional
    fun getCommentList(option:String,value:String): List<Comment> {
        val query= Query(Criteria.where(option).`is`(value))
        return mongoTemplate.find(query, CommentEntity::class.java).map {
            entity: CommentEntity ->
            Comment(
                entity._id.toHexString(),
                entity.postID,
                entity.userName,
                entity.content
            )
        }
    }
}