package com.praxis.qrcode.application.service;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.praxis.qrcode.config.properties.S3Properties;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Slf4j
@Service
public class S3Service {

    private final AmazonS3 amazonS3;

    private final S3Properties s3Properties;

    private final static long tenYearsInSeconds = 10 * 365 * 24 * 60 * 60L;

    public S3Service(S3Properties s3Properties) {
        this.s3Properties = s3Properties;
        AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(
                        s3Properties.getAccessKeyId(),
                        s3Properties.getSecretKeyId()
                )
        );
        this.amazonS3 = AmazonS3ClientBuilder.standard()
                .withRegion(s3Properties.getRegion())
                .withCredentials(credentialsProvider)
                .build();
    }

    @SneakyThrows(FileNotFoundException.class)
    public String saveS3Bucket(File file, String prefix) {
        log.info("S3Service :: Iniciando a persistencia do arquivo no bucket");
        String defaultBucket = s3Properties.getBucket();
        log.info("S3Service :: Salvando arquigo no Bucket: " + defaultBucket);
        String storedFilePath = prefix + file.getName();
        amazonS3.putObject(new PutObjectRequest(defaultBucket, storedFilePath, new FileInputStream(file), getMetadata(file)));
        URL s3Url = amazonS3.getUrl(s3Properties.getBucket(), storedFilePath);
        log.info("S3Service :: Url da imagem:" + s3Url.toString());
        return createPublicUrl(s3Url.toString());
    }

    @SneakyThrows(IOException.class)
    private ObjectMetadata getMetadata(File file){
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setCacheControl("max-age=" + tenYearsInSeconds);
        metadata.setContentType(new Tika().detect(file));
        metadata.setContentLength(file.length());
        return metadata;
    }

    public String extractObjectName(String url) {
        final String QUERY = ".amazonaws.com/";
        return url.substring(url.lastIndexOf(QUERY) + QUERY.length());
    }

    public String createPublicUrl(String fileUrl) {
        String defaultBucket = s3Properties.getBucket();
        String key = extractObjectName(fileUrl);
        Date expiration = Date.from(LocalDateTime.now().plusMinutes(15).toInstant(ZoneOffset.ofHours(-3)));
        return amazonS3.generatePresignedUrl(defaultBucket, key, expiration).toString();
    }
}
