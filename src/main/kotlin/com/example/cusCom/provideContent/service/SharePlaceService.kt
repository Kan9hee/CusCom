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
    fun loadPost(option:String,value:String): SharePlacePost? {
        val query= Query(Criteria.where(option).`is`(if(option==dbStringConfig.mongodb.id) ObjectId(value) else value))
        val entity = mongoTemplate.findOne(query, SharePlacePostEntity::class.java)
        return entity?.let {
            increaseViewCount(it)
            SharePlacePost(
                it._id.toHexString(),
                it.estimateID,
                it.title,
                it.userName,
                it.thumbnail,
                it.tags,
                it.viewCount,
                it.likeCount,
                it.commentCount)
        }
    }

    @Transactional
    fun getPostList(maxContent:Int,currentPage:Int): HashMap<String,Any> {
        val posts=mongoTemplate.findAll(SharePlacePostEntity::class.java).map {
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
                entity.commentCount
            )
        }

        return pagination(posts,maxContent,currentPage)
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
        val posts=mongoTemplate.find(Query(condition),SharePlacePostEntity::class.java).map {
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
                    entity.commentCount
                )
        }
        return pagination(posts,maxContent,currentPage)
    }

    @Transactional
    fun increaseViewCount(sharePlacePostEntity: SharePlacePostEntity){
        val query= Query(Criteria.where(dbStringConfig.mongodb.id).`is`(sharePlacePostEntity._id))
        val update= Update().inc(innerStringsConfig.property.viewCount,innerStringsConfig.property.changeValue)
        mongoTemplate.updateFirst(query,update,dbStringConfig.mongodb.collection.post)
    }

    @Transactional
    fun increaseCommentCount(sharePlacePostID: String){
        val query= Query(Criteria.where(dbStringConfig.mongodb.id).`is`(ObjectId(sharePlacePostID)))
        val update= Update().inc(innerStringsConfig.property.commentCount,innerStringsConfig.property.changeValue)
        mongoTemplate.updateFirst(query,update,dbStringConfig.mongodb.collection.post)
    }

    @Transactional
    fun uploadComment(comment: Comment){
        if(comment.content.isEmpty())
            throw CusComException(CusComErrorCode.NotWrittenComment)

        mongoTemplate.insert(
            CommentEntity(
            ObjectId(),
            comment.postID,
            comment.userName,
            comment.content)
        )
        increaseCommentCount(comment.postID)
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
                entity.content
            )
        }
    }

    fun findInEstimates(value: String): Criteria {
        val excludedFields = setOf(
            dbStringConfig.mongodb.id,
            innerStringsConfig.property.userName,
            innerStringsConfig.property.postCheck,
            dbStringConfig.mongodb.mongoClass
        )
        val orCriteria = mutableListOf<Criteria>()
        val estimateEntities = mongoTemplate.find(Query(), EstimateEntity::class.java)
        val fields = EstimateEntity::class.java.declaredFields

        for (estimateEntity in estimateEntities) {
            val fieldsToCheck = fields
                .filter { !excludedFields.contains(it.name) }
                .map { field ->
                    field.isAccessible = true
                    field.get(estimateEntity)?.toString()
                }
            val entityString = fieldsToCheck.joinToString(separator = ", ")
            if (entityString.contains(value, ignoreCase = true)) {
                orCriteria.add(Criteria.where(dbStringConfig.mongodb.id).`is`(estimateEntity._id))
            }
        }

        if (orCriteria.isNotEmpty()) {
            val estimateCriteria = Criteria().orOperator(*orCriteria.toTypedArray())
            val estimateEntities = mongoTemplate.find(Query(estimateCriteria), EstimateEntity::class.java)
            if (estimateEntities.isNotEmpty()) {
                val estimateIds = estimateEntities.map { it._id.toHexString() }
                return Criteria.where(innerStringsConfig.request.estimate.id).`in`(estimateIds)
            }
        }
        return Criteria()
    }


    private fun pagination(posts:List<SharePlacePost>, maxContent:Int, currentPage:Int): HashMap<String,Any> {
        val postCount=posts.size
        val startContent = if(currentPage == 1) 0 else (currentPage-1)*maxContent
        val endContent = if(startContent+maxContent>postCount) postCount
                         else currentPage*maxContent

        val map=HashMap<String,Any>()
        map[innerStringsConfig.postListMapper.postList]=posts.subList(startContent,endContent)
        map[innerStringsConfig.postListMapper.pageCount]=if(postCount%maxContent==0) (postCount/maxContent) else (postCount/maxContent+1)
        map[innerStringsConfig.postListMapper.currentPage]=currentPage

        return map
    }
}