package com.praxis.qrcode.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;

import java.io.File;
import java.nio.file.Paths;
import java.util.Objects;

import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Region;
import com.praxis.qrcode.config.properties.S3Properties;
import com.praxis.qrcode.utils.TempFileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = {S3Service.class})
@DisplayName("S3Service - Classe de teste unitario")
class S3ServiceTest {

    private static AmazonS3 amazonS3;

    private static File arquivo;

    @Autowired
    private static S3Service s3Service;

    @BeforeAll
    static void setUp() {
        var acess = "AKIAZQN7FOEBHG5SFVO5";
        var secret = "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";
        AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(new BasicAWSCredentials(acess, secret));
        amazonS3 = AmazonS3ClientBuilder.standard()
                .withRegion(Region.SA_SaoPaulo.toString())
                .withCredentials(credentialsProvider)
                .build();
        S3Properties properties = new S3Properties();
        properties.setRegion(amazonS3.getRegionName());
        properties.setAccessKeyId(acess);
        properties.setSecretKeyId(secret);
        s3Service = new S3Service(properties);
        String filePath = TempFileUtils.getFilePath(TempFileUtils.createNewFileName(".png"));
        arquivo = new File(Objects.requireNonNull(filePath));
    }


    /**
     * Method under test: {@link S3Service#saveS3Bucket(File, String)}
     */
    @Test
    void testSaveS3Bucket() {
        var bucket = "dev-bucket-yagovcb";
        String retorno = s3Service.saveS3Bucket(arquivo, "qr-code");
        assertTrue(retorno.contains(bucket));
    }

    /**
     * Method under test: {@link S3Service#extractObjectName(String)}
     */
    @Test
    void testExtractObjectName() {
        when(s3Service.extractObjectName((String) any())).thenReturn("Extract Object Name");
        assertEquals("Extract Object Name", s3Service.extractObjectName("https://example.org/example"));
        verify(s3Service).extractObjectName((String) any());
    }

    /**
     * Method under test: {@link S3Service#createPublicUrl(String)}
     */
    @Test
    void testCreatePublicUrl() {
        when(s3Service.createPublicUrl((String) any())).thenReturn("https://example.org/example");
        assertEquals("https://example.org/example", s3Service.createPublicUrl("https://example.org/example"));
        verify(s3Service).createPublicUrl((String) any());
    }

}
