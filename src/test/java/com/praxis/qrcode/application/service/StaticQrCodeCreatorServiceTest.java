package com.praxis.qrcode.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.praxis.qrcode.domain.mock.StaticQrCodeRequestMock;
import com.praxis.qrcode.infrastructure.request.StaticQrCodeRequest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = {StaticQrCodeCreatorService.class})
@DisplayName("StaticQrCodeCreatorService - Classe de teste unitario")
class StaticQrCodeCreatorServiceTest {

    @Autowired
    private StaticQrCodeCreatorService staticQrCodeCreatorService;

    @Test
    @Order(1)
    @DisplayName("Teste de serviço que tenta criar um QrCode Copia e Cola")
    void testCreate() {
        //preparacao
        StaticQrCodeRequest nossoMock = StaticQrCodeRequestMock.getStaticQrCodeRequest();
        StaticQrCodeRequest objectoMockado = mock(StaticQrCodeRequest.class);

        //ensina o spring
        when(objectoMockado.getValue()).thenReturn(nossoMock.getValue());
        when(objectoMockado.getKey()).thenReturn(nossoMock.getKey());
        when(objectoMockado.getMessage()).thenReturn(nossoMock.getMessage());
        when(objectoMockado.getNomeUsuarioFinal()).thenReturn(nossoMock.getNomeUsuarioFinal());
        when(objectoMockado.getTxId()).thenReturn(nossoMock.getTxId());

        //faz a requisição
        String retornoCreate = this.staticQrCodeCreatorService.create(objectoMockado);

        //Fazer os Asserts
        assertNotNull(retornoCreate);
        verify(objectoMockado).getKey();
        verify(objectoMockado).getMessage();
        verify(objectoMockado).getNomeUsuarioFinal();
        verify(objectoMockado).getTxId();
        verify(objectoMockado, atLeast(1)).getValue();
    }

    @Test
    @Order(2)
    @DisplayName("Teste de serviço que tenta criar um QrCode Copia e Cola forçando NullPointer")
    void testCreate_nullPointerException() {
        assertThrows(NullPointerException.class, () -> this.staticQrCodeCreatorService.create(StaticQrCodeRequest.builder().build()));
    }

    @Test
    @Order(3)
    @DisplayName("Teste de serviço que tenta criar um QrCode Copia e Cola forçando '***' no retorno do QRCode")
    void testCreateRetrieveTransactionId() {
        StaticQrCodeRequest staticQrCodeRequest = new StaticQrCodeRequest();
        staticQrCodeRequest.setNomeUsuarioFinal("01");
        assertTrue(staticQrCodeCreatorService.create(staticQrCodeRequest).contains("***"));
    }

}
