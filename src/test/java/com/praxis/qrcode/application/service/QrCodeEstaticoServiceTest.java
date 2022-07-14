package com.praxis.qrcode.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.praxis.qrcode.domain.mock.StaticQrCodeRequestMock;
import com.praxis.qrcode.infrastructure.request.StaticQrCodeRequest;
import com.praxis.qrcode.infrastructure.response.StaticQrCodeResponse;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = {QrCodeEstaticoService.class})
@DisplayName("QrCodeEstaticoService - Classe de teste unitario")
class QrCodeEstaticoServiceTest {

    @Autowired
    private QrCodeEstaticoService qrCodeEstaticoService;

    @MockBean
    private QrCodeImageGeneratorService qrCodeImageGeneratorService;

    @MockBean
    private StaticQrCodeCreatorService staticQrCodeCreatorService;


    @Test
    @Order(1)
    @DisplayName("Teste de Serviço que tenta criar um QRCode com dados vazio")
    void testCriaQrCodeEstatico() {
        when(staticQrCodeCreatorService.create(any())).thenReturn("Create");
        when(qrCodeImageGeneratorService.generate(any())).thenReturn("Generate");
        StaticQrCodeResponse actualCriaQrCodeEstaticoResult = qrCodeEstaticoService.criaQrCodeEstatico(StaticQrCodeRequest.builder().build());
        assertEquals("Generate", actualCriaQrCodeEstaticoResult.getImagemUrl());
        assertEquals("Create", actualCriaQrCodeEstaticoResult.getQrCode());
        verify(staticQrCodeCreatorService).create(any());
        verify(qrCodeImageGeneratorService).generate(any());
    }

    /**
     * Method under test: {@link QrCodeEstaticoService#criaQrCodeEstatico(StaticQrCodeRequest)}
     */
    @Test
    @Order(2)
    @DisplayName("Teste de Serviço que tenta criar um QRCode com dados mockados")
    void testCriaQrCodeEstatico_DadosMockados() {
        when(staticQrCodeCreatorService.create(any())).thenReturn("Create");
        when(qrCodeImageGeneratorService.generate(any())).thenReturn("Generate");
        StaticQrCodeRequest staticQrCodeRequest = StaticQrCodeRequestMock.getStaticQrCodeRequest();

        StaticQrCodeResponse actualCriaQrCodeEstaticoResult = qrCodeEstaticoService.criaQrCodeEstatico(staticQrCodeRequest);
        assertEquals("Create", actualCriaQrCodeEstaticoResult.getQrCode());
        assertEquals("Generate", actualCriaQrCodeEstaticoResult.getImagemUrl());
        assertEquals(staticQrCodeRequest.getTxId(), actualCriaQrCodeEstaticoResult.getTxId());

        verify(staticQrCodeCreatorService).create(any());
        verify(qrCodeImageGeneratorService).generate(any());
    }

    /**
     * Method under test: {@link QrCodeEstaticoService#criaQrCodeEstatico(StaticQrCodeRequest)}
     */
    @Test
    @Order(3)
    @DisplayName("Teste de Serviço que tenta criar um QRCode dados nulos")
    void testCriaQrCodeEstatico_nullPointerException() {
        assertThrows(NullPointerException.class, () -> this.qrCodeEstaticoService.criaQrCodeEstatico(null));
    }
}
