package com.praxis.qrcode.application.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.praxis.qrcode.application.service.QrCodeEstaticoService;
import com.praxis.qrcode.domain.mock.StaticQrCodeRequestMock;
import com.praxis.qrcode.infrastructure.request.StaticQrCodeRequest;
import com.praxis.qrcode.infrastructure.response.StaticQrCodeResponse;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {QrCodeEstaticoController.class})
@ExtendWith(SpringExtension.class)
class QrCodeEstaticoControllerTest {
    @Autowired
    private QrCodeEstaticoController qrCodeEstaticoController;

    @MockBean
    private QrCodeEstaticoService qrCodeEstaticoService;

    /**
     * Method under test: {@link QrCodeEstaticoController#criarQrCodeEstatico(StaticQrCodeRequest)}
     */
    @Test
    void testCriarQrCodeEstatico() throws Exception {
        when(qrCodeEstaticoService.criaQrCodeEstatico((StaticQrCodeRequest) any()))
                .thenReturn(new StaticQrCodeResponse("Qr Code", "https://example.org/example", "42", "2020-03-01"));

        StaticQrCodeRequest staticQrCodeRequest = StaticQrCodeRequestMock.getStaticQrCodeRequest();
        String content = (new ObjectMapper()).writeValueAsString(staticQrCodeRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/qr-code/estatico/criar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(qrCodeEstaticoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"qrCode\":\"Qr Code\",\"imagemUrl\":\"https://example.org/example\",\"txId\":\"42\",\"transactionDateTime\":\"2020"
                                        + "-03-01\"}"));
    }
}

