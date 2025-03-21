package com.example.cusCom.dto

data class SharePlacePostDTO(var _id:String="",
                             var estimateID:String="",
                             var title:String="",
                             val userName:String="",
                             val thumbnail:String="",
                             var tags:Array<String>,
                             var viewCount:Long=0,
                             var likeCount:Long=0,
                             var commentCount:Long=0){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SharePlacePostDTO

        if (!tags.contentEquals(other.tags)) return false

        return true
    }

    override fun hashCode(): Int {
        return tags.contentHashCode()
    }
}