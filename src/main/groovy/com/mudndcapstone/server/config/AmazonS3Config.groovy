package com.mudndcapstone.server.config

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AmazonS3Config {

    @Value('${aws.access.key.id}')
    private String awsKeyId

    @Value('${aws.access.key.secret}')
    private String awsKeySecret

    @Value('${aws.region}')
    private String awsRegion

    @Value('${aws.s3.audio.bucket}')
    private String awsS3AudioBucket

    @Bean(name = "awsKeyId")
    String getAWSKeyId() {
        awsKeyId
    }

    @Bean(name = "awsKeySecret")
    String getAWSKeySecret() {
        awsKeySecret
    }

    @Bean(name = "awsRegion")
    Region getAWSPollyRegion() {
        Region.getRegion(Regions.fromName(awsRegion))
    }

    @Bean(name = "awsCredentialsProvider")
    AWSCredentialsProvider getAWSCredentials() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(this.awsKeyId, this.awsKeySecret)
        new AWSStaticCredentialsProvider(awsCredentials)
    }

    @Bean(name = "awsS3AudioBucket")
    String getAWSS3AudioBucket() {
        awsS3AudioBucket
    }

    @Bean(name = "amazonS3")
    AmazonS3 getAmazonS3() {
        AmazonS3ClientBuilder.standard()
            .withCredentials(getAWSCredentials())
            .withRegion(getAWSPollyRegion().getName()).build()
    }
}
