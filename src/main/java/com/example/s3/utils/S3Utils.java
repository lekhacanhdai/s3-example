package com.example.s3.utils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lkadai0801
 * @since 16/12/2022
 */

@Service
public class S3Utils {
    private AmazonS3 s3;

    @Value("${aws.s3.endpoint-public}")
    private String endpointUrl;

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    @Value("${aws.s3.access_key}")
    private String accessKey;

    @Value("${aws.s3.secret_access_key}")
    private String secretKey;

    @PostConstruct
    private void initialConstructor(){
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3 = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.AP_SOUTH_1)
                .build();

    }

    public List<String> getAllKey(){
        return s3.listObjects(this.bucketName).getObjectSummaries().stream().map(o -> this.endpointUrl + "/" + o.getKey()).collect(Collectors.toUnmodifiableList());
    }

    public void uploadObjectToS3(String key, InputStream inputStream, String contentType){
        ObjectMetadata objectMetadata = new ObjectMetadata();
        if (contentType.contains("image")){
            objectMetadata.setContentType("image/jpeg");
        }
        s3.putObject(this.bucketName, key, inputStream, objectMetadata/* FIXME */);
    }

}
