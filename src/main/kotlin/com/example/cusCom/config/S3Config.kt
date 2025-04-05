package com.example.cusCom.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.presigner.S3Presigner

@Configuration
class S3Config(private val innerStringsConfig: InnerStringsConfig) {

    @Bean
    fun s3Client(): S3Client = S3Client.builder()
        .region(Region.of(innerStringsConfig.property.aws.region))
        .credentialsProvider(commonAwsCredentialsProvider())
        .build()

    @Bean
    fun s3Presigner(): S3Presigner = S3Presigner.builder()
        .region(Region.of(innerStringsConfig.property.aws.region))
        .credentialsProvider(commonAwsCredentialsProvider())
        .build()

    private fun commonAwsCredentialsProvider() = StaticCredentialsProvider.create(
        AwsBasicCredentials.create(
            innerStringsConfig.property.aws.accessKey,
            innerStringsConfig.property.aws.secretKey)
    )
}