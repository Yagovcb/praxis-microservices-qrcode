package com.praxis.qrcode.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.praxis.qrcode.PraxisMicroservicesQrcodeApplication;
import com.praxis.qrcode.application.service.QrCodeEstaticoService;
import com.praxis.qrcode.application.service.QrCodeImageGeneratorService;
import com.praxis.qrcode.application.service.S3Service;
import com.praxis.qrcode.application.service.StaticQrCodeCreatorService;
import com.praxis.qrcode.config.properties.S3Properties;
import com.praxis.qrcode.domain.mock.StaticQrCodeRequestMock;
import com.praxis.qrcode.infrastructure.request.StaticQrCodeRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(classes = {QrCodeEstaticoController.class, PraxisMicroservicesQrcodeApplication.class})
@ActiveProfiles(value = "test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("QrCodeEstaticoControllerIT - Teste de integração da API de compras")
class QrCodeEstaticoControllerIT {

    private static final String URI_BASE = "/v1/qr-code/estatico";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc restQrCodeMockMvc;

    @Autowired
    private QrCodeEstaticoService service;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private StaticQrCodeCreatorService staticQrCodeCreatorService;

    @Autowired
    private QrCodeImageGeneratorService qrCodeImageGeneratorService;

    @Autowired
    @Qualifier("s3properties")
    private S3Properties s3Properties;

    @Test
    void postCriarQrCodeEstaticoTest() throws Exception {
        StaticQrCodeRequest request = StaticQrCodeRequestMock.getStaticQrCodeRequest();

        restQrCodeMockMvc.perform(post(URI_BASE + "/criar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.txId").value(request.getTxId()));
    }
}
