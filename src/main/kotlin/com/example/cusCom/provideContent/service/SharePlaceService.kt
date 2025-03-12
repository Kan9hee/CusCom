package com.example.cusCom.provideContent.service

import com.example.cusCom.config.DBStringConfig
import com.example.cusCom.config.InnerStringsConfig
import com.example.cusCom.exception.CusComErrorCode
import com.example.cusCom.exception.CusComException
import com.example.cusCom.provideContent.dto.Comment
import com.example.cusCom.provideContent.dto.SharePlacePost
import com.example.cusCom.provideContent.entity.mongoDB.CommentEntity
import com.example.cusCom.provideContent.entity.mongoDB.EstimateEntity
import com.example.cusCom.provideContent.entity.mongoDB.SharePlacePostEntity
import org.bson.types.ObjectId
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.FindAndModifyOptions
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SharePlaceService(private val mongoTemplate: MongoTemplate,
                        private val innerStringsConfig: InnerStringsConfig,
                        private val dbStringConfig: DBStringConfig,
                        private val estimateService: EstimateService,
                        private val desktopPartsService: DesktopPartsService) {

    @Transactional
    fun uploadPost(sharePlacePost: SharePlacePost){
        if(sharePlacePost.title.isEmpty())
            throw CusComException(CusComErrorCode.UnfinishedPost)

        val estimate=estimateService.getUserEstimateById(sharePlacePost.estimateID)
        val caseImage=desktopPartsService.findCase(innerStringsConfig.property.findOption.name,estimate.desktopCase).imageUrl
        mongoTemplate.insert(
            SharePlacePostEntity(
                ObjectId(),
                sharePlacePost.estimateID,
                sharePlacePost.title,
                sharePlacePost.userName,
                caseImage,
                sharePlacePost.tags,
                sharePlacePost.viewCount,
                sharePlacePost.likeCount,
                sharePlacePost.commentCount
            )
        )
    }

    @Transactional
    fun deletePost(option:String,value:String){
        val query=Query(Criteria.where(option).`is`(if(option==dbStringConfig.mongodb.id) ObjectId(value) else value))
        val result=mongoTemplate.remove(query, SharePlacePostEntity::class.java)

        if(result.deletedCount==0L)
            throw CusComException(CusComErrorCode.FailedDeletePost)
    }

    @Transactional
    fun loadPost(option: String, value: String): SharePlacePost {
        val query = Query(Criteria.where(option).`is`(if (option == dbStringConfig.mongodb.id) ObjectId(value) else value))

        val update = Update().inc(
            innerStringsConfig.property.viewCount,
            innerStringsConfig.property.changeValue)

        val entity = mongoTemplate.findAndModify(
            query,
            update,
            FindAndModifyOptions.options().returnNew(true),
            SharePlacePostEntity::class.java)
            ?: throw CusComException(CusComErrorCode.PostNotFound)

        return entity.let {
            SharePlacePost(
                it._id.toHexString(),
                it.estimateID,
                it.title,
                it.userName,
                it.thumbnail,
                it.tags,
                it.viewCount,
                it.likeCount,
                it.commentCount
            )
        }
    }

    @Transactional
    fun getPostList(maxContent:Int,currentPage:Int): HashMap<String,Any> {
        val query = Query()
            .with(Sort.by(Sort.Direction.DESC, "createdAt"))
            .skip((currentPage - 1) * maxContent.toLong())
            .limit(maxContent)

        val posts=mongoTemplate.find(query, SharePlacePostEntity::class.java).map { entity ->
            SharePlacePost(
                entity._id.toHexString(),
                entity.estimateID,
                entity.title,
                entity.userName,
                entity.thumbnail,
                entity.tags,
                entity.viewCount,
                entity.likeCount,
                entity.commentCount)
        }

        return hashMapOf(
            innerStringsConfig.postListMapper.postList to posts,
            innerStringsConfig.postListMapper.pageCount to getTotalPageCount(maxContent),
            innerStringsConfig.postListMapper.currentPage to currentPage
        )
    }

    @Transactional
    fun searchPost(option:String,value:String,maxContent:Int,currentPage:Int): HashMap<String, Any> {
        val condition=when(option){
            innerStringsConfig.property.postSearchOption.title ->Criteria.where(option).regex(".*$value.*","i")
            innerStringsConfig.property.postSearchOption.tags -> Criteria.where(option).`in`(value)
            innerStringsConfig.property.userName->Criteria.where(option).`is`(value)
            innerStringsConfig.property.postSearchOption.parts->findInEstimates(value)
            else -> Criteria()
        }

        val query = Query(condition)
            .with(Sort.by(Sort.Direction.DESC, "createdAt"))
            .skip((currentPage - 1) * maxContent.toLong())
            .limit(maxContent)

        val posts=mongoTemplate.find(query,SharePlacePostEntity::class.java).map {
                entity: SharePlacePostEntity ->
                SharePlacePost(
                    entity._id.toHexString(),
                    entity.estimateID,
                    entity.title,
                    entity.userName,
                    entity.thumbnail,
                    entity.tags,
                    entity.viewCount,
                    entity.likeCount,
                    entity.commentCount)
        }

        return hashMapOf(
            innerStringsConfig.postListMapper.postList to posts,
            innerStringsConfig.postListMapper.pageCount to getTotalPageCount(maxContent),
            innerStringsConfig.postListMapper.currentPage to currentPage
        )
    }

    @Transactional
    fun uploadComment(comment: Comment){
        if(comment.content.isEmpty())
            throw CusComException(CusComErrorCode.NotWrittenComment)

        val commentEntity = CommentEntity(ObjectId(), comment.postID, comment.userName, comment.content)

        val query= Query(Criteria.where(dbStringConfig.mongodb.id).`is`(ObjectId(comment.postID)))
        val update= Update()
            .push(dbStringConfig.mongodb.collection.comment, commentEntity)
            .inc(innerStringsConfig.property.commentCount,innerStringsConfig.property.changeValue)

        val updatedPost = mongoTemplate.findAndModify(
            query,
            update,
            FindAndModifyOptions.options().returnNew(true),
            SharePlacePostEntity::class.java)
            ?: throw CusComException(CusComErrorCode.PostNotFound)
    }

    @Transactional
    fun deleteComment(option:String,value:String){
        val query= Query(Criteria.where(option).`is`(if(option==dbStringConfig.mongodb.id) ObjectId(value) else value))
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
                entity.content)
        }
    }

    fun findInEstimates(value: String): Criteria {
        val excludedFields = setOf(
            dbStringConfig.mongodb.id,
            innerStringsConfig.property.userName,
            innerStringsConfig.property.postCheck,
            dbStringConfig.mongodb.mongoClass)

        val fields = EstimateEntity::class.java.declaredFields
            .filter { !excludedFields.contains(it.name) }
            .map { it.name }

        val orCriteria = fields.map { field ->
            Criteria.where(field).regex(".*$value.*", "i")
        }

        return if (orCriteria.isNotEmpty()) {
            val estimateCriteria = Criteria().orOperator(*orCriteria.toTypedArray())
            val estimateIds = mongoTemplate.find(Query(estimateCriteria), EstimateEntity::class.java)
                .map { it._id.toHexString() }

            if (estimateIds.isNotEmpty()) {
                Criteria.where(innerStringsConfig.request.estimate.id).`in`(estimateIds)
            } else {
                Criteria()
            }
        } else {
            Criteria()
        }
    }

    private fun getTotalPageCount(maxContent:Int): Long {
        val totalCount = mongoTemplate.count(Query(), SharePlacePostEntity::class.java)
        if (totalCount % maxContent == 0L)
            return (totalCount / maxContent)
        return (totalCount / maxContent + 1)
    }
}