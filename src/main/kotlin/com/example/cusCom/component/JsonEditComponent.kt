package com.example.cusCom.component

import com.example.cusCom.config.InnerStringsConfig
import com.example.cusCom.exception.CusComErrorCode
import com.example.cusCom.exception.CusComException
import com.example.cusCom.service.BlobService
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class JsonEditComponent(private val innerStringsConfig: InnerStringsConfig,
                        private val blobService: BlobService) {

     fun injectImageUrlToJson(json:String,image: MultipartFile): String {
        val jsonObject = Gson().fromJson(json, JsonObject::class.java)
        val contentType=image.contentType

        if (contentType != null && contentType.startsWith("image/")) {
            val imageUrlString = blobService.uploadImage(
                image,
                innerStringsConfig.property.imageWidth,
                innerStringsConfig.property.imageHeight)
            jsonObject.addProperty(
                innerStringsConfig.property.imageUrl,
                imageUrlString)
        }
        else throw CusComException(CusComErrorCode.NotImageData)

        return Gson().toJson(jsonObject)
    }
}