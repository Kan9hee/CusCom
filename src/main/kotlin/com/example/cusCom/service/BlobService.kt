package com.example.cusCom.service

import com.azure.storage.blob.BlobClient
import com.azure.storage.blob.BlobContainerClient
import com.azure.storage.blob.BlobContainerClientBuilder
import com.example.cusCom.config.DBStringConfig
import org.imgscalr.Scalr
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

@Service
class BlobService(private val dbStringConfig: DBStringConfig){

    private val containerClient:BlobContainerClient=BlobContainerClientBuilder()
        .connectionString(System.getenv("connectionString"))
        .containerName(dbStringConfig.blob.containerName)
        .buildClient()

    @Transactional
    fun uploadImage(image:MultipartFile,widthSize:Int,heightSize:Int):String{
        val byteArrayOutputStream=resizeImage(image,widthSize,heightSize)
        val resizedInputImage=ByteArrayInputStream(byteArrayOutputStream.toByteArray())
        val blobClient:BlobClient=containerClient.getBlobClient(image.originalFilename)
        blobClient.upload(resizedInputImage,byteArrayOutputStream.size().toLong(),true)
        return blobClient.blobUrl
    }

    private fun resizeImage(image:MultipartFile,widthSize:Int,heightSize:Int): ByteArrayOutputStream {
        val bufferedImage = ImageIO.read(image.inputStream)
        val resizedImage = Scalr.resize(bufferedImage,Scalr.Method.ULTRA_QUALITY,widthSize,heightSize)
        val byteArrayOutputStream = ByteArrayOutputStream()
        ImageIO.write(resizedImage,dbStringConfig.blob.fileFormat,byteArrayOutputStream)

        return byteArrayOutputStream
    }
}