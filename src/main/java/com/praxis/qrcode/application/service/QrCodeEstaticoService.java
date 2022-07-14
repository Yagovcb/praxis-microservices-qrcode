package com.praxis.qrcode.application.service;

import com.praxis.qrcode.infrastructure.request.StaticQrCodeRequest;
import com.praxis.qrcode.infrastructure.response.StaticQrCodeResponse;
import com.praxis.qrcode.utils.TransactionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class QrCodeEstaticoService {

    private final StaticQrCodeCreatorService staticQrCodeCreatorService;
    private final QrCodeImageGeneratorService qrCodeImageGeneratorService;

    public StaticQrCodeResponse criaQrCodeEstatico(StaticQrCodeRequest request) {
        request.setTxId(Objects.isNull(request.getTxId()) ? TransactionUtils.gerarIdTransacao(25) : request.getTxId());
        log.debug("QrCodeEstaticoService :: TxId da criação do QRCode atual - {}", request.getTxId());
        String qrCode = staticQrCodeCreatorService.create(request);
        log.info("QrCodeEstaticoService :: QrCode Criado com sucesso!");
        String imageUrl = qrCodeImageGeneratorService.generate(qrCode);
        log.info("QrCodeEstaticoService :: Imagem do QrCode Criado com sucesso!");
        return StaticQrCodeResponse.builder()
                .qrCode(qrCode)
                .imagemUrl(imageUrl)
                .txId(request.getTxId())
                .transactionDateTime(String.valueOf(LocalDateTime.now()))
                .build();
    }
}
