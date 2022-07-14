package com.praxis.qrcode.application.service;

import com.amazonaws.services.s3.AmazonS3;
import com.praxis.qrcode.config.QrCodeTestConfiguration;
import com.praxis.qrcode.config.properties.S3Properties;
import com.praxis.qrcode.domain.mock.StaticQrCodeRequestMock;
import com.praxis.qrcode.infrastructure.request.StaticQrCodeRequest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = {QrCodeImageGeneratorService.class})
@DisplayName("QrCodeImageGeneratorService - Classe de teste unitario")
@Import(QrCodeTestConfiguration.class)
class QrCodeImageGeneratorServiceTest {

    @Autowired
    private QrCodeImageGeneratorService qrCodeImageGeneratorService;

    @Autowired
    private S3Properties s3Properties;

    @MockBean
    private S3Service s3Service;

    @MockBean
    private StaticQrCodeCreatorService staticQrCodeCreatorService;

    @Test
    void generateTest(){
        String content = "TesteDeGeracaoDeQrCodeEstatico";
        when(qrCodeImageGeneratorService.generate("Teste")).thenReturn("Generate");

        String retorno = this.qrCodeImageGeneratorService.generate(content);
        assertNull(retorno);
    }
}
