package com.praxis.qrcode.domain.mock;

import com.praxis.qrcode.infrastructure.request.StaticQrCodeRequest;
import com.praxis.qrcode.utils.TransactionUtils;

import java.math.BigDecimal;

public class StaticQrCodeRequestMock {
    public static StaticQrCodeRequest getStaticQrCodeRequest(){
        return StaticQrCodeRequest.builder()
                .key("yago.vcb@gmail.com")
                .txId(TransactionUtils.gerarIdTransacao(25))
                .message("rotina de testes")
                .nomeUsuarioFinal("Yago do Valle Castelo Branco")
                .phone("91983547174")
                .value(BigDecimal.valueOf(42L))
                .build();
    }

}
