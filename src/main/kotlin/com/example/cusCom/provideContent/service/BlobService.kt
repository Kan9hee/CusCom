package com.example.cusCom.provideContent.service

import com.azure.storage.blob.BlobClient
import com.azure.storage.blob.BlobContainerClient
import com.azure.storage.blob.BlobContainerClientBuilder
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException
import java.io.IOException

@Service
class BlobService{

    private val containerClient:BlobContainerClient=BlobContainerClientBuilder()
        .connectionString(System.getProperty("connectionString"))
        .containerName("desktop-parts-images")
        .buildClient()

    fun uploadImage(image:MultipartFile):String{
        val blobClient:BlobClient=containerClient.getBlobClient(image.originalFilename)
        try{
            blobClient.upload(image.inputStream,image.size,true)
        }catch(e:IOException){
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)
        }

        return blobClient.blobUrl
    }
}