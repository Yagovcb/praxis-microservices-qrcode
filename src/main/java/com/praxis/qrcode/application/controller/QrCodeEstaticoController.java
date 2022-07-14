package com.praxis.qrcode.application.controller;

import com.praxis.qrcode.application.service.QrCodeEstaticoService;
import com.praxis.qrcode.infrastructure.request.StaticQrCodeRequest;
import com.praxis.qrcode.infrastructure.response.StaticQrCodeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/qr-code/estatico")
public class QrCodeEstaticoController {

    private final QrCodeEstaticoService service;

    @PostMapping(value = "/criar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaticQrCodeResponse> criarQrCodeEstatico(@RequestBody StaticQrCodeRequest request) {
        log.info("QrCodeEstaticoController :: Iniciando a criação do QrCode Estatico");
        return ResponseEntity.ok(service.criaQrCodeEstatico(request));
    }
}
