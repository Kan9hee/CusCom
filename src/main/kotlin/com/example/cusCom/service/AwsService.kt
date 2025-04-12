package com.example.cusCom.service

import com.example.cusCom.config.InnerStringsConfig
import org.imgscalr.Scalr
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

@Service
class AwsService(private val innerStringsConfig: InnerStringsConfig,
                 private val s3Client: S3Client){

    fun uploadImage(image: MultipartFile): String {
        val fullPath = "${innerStringsConfig.property.aws.folder}/${image.originalFilename}"
        val putObjectRequest = PutObjectRequest.builder()
            .bucket(innerStringsConfig.property.aws.bucket)
            .key(fullPath)
            .contentType(image.contentType)
            .build()

        val resizedBytes = resizeImage(image)
        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(resizedBytes))
        return "https://${innerStringsConfig.property.aws.bucket}.s3.${innerStringsConfig.property.aws.region}.amazonaws.com/$fullPath"
    }

    private fun resizeImage(image:MultipartFile): ByteArray {
        val bufferedImage: BufferedImage = ImageIO.read(image.inputStream)
        val resizedImage = Scalr.resize(
            bufferedImage,
            Scalr.Method.ULTRA_QUALITY,
            innerStringsConfig.property.aws.defaultWidth,
            innerStringsConfig.property.aws.defaultHeight)
        val byteArrayOutputStream = ByteArrayOutputStream()
        ImageIO.write(resizedImage, image.contentType, byteArrayOutputStream)
        return byteArrayOutputStream.toByteArray()
    }
}