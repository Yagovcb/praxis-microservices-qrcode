package com.praxis.qrcode.application.service;

import com.praxis.qrcode.infrastructure.client.*;
import com.praxis.qrcode.infrastructure.request.StaticQrCodeRequest;
import com.praxis.qrcode.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StaticQrCodeCreatorService {
    private static final String CIDADE_EFETUACAO_TRANSACAO = "BELEM";

    public String create(StaticQrCodeRequest request) {
            MerchantAccountInformationTemplate merchantAccountInformationTemplate = MerchantAccountInformationTemplate.builder()
                    .chave(new BRCodeField(BRCodeId.CHAVE, request.getKey()))
                    .mensagem(new BRCodeField(BRCodeId.MESSAGE, request.getMessage()))
                    .build();

            AdditionalDataFieldTemplate additionalDataFieldTemplate = AdditionalDataFieldTemplate.builder()
                    .txId(new BRCodeField(BRCodeId.TRANSACTION_ID, retrieveTransactionId(request.getTxId())))
                    .build();

            BRCodeTemplate brCode = BRCodeTemplate.builder()
                    .merchantAccountInformation(new BRCodeField(BRCodeId.MERCHANT_ACCOUNT_INFORMATION, merchantAccountInformationTemplate.toString()))
                    .merchantCategoryCode(new BRCodeField(BRCodeId.MERCHANT_CATEGORY_CODE, "0000"))
                    .transactionCurrency(new BRCodeField(BRCodeId.TRANSACTION_CURRENCY, "986"))
                    .merchantName(new BRCodeField(BRCodeId.MERCHANT_NAME, this.getMerchantName(request.getNomeUsuarioFinal())))
                    .merchantCity(new BRCodeField(BRCodeId.MERCHANT_CITY, CIDADE_EFETUACAO_TRANSACAO))
                    .additionalDataFieldTemplate(new BRCodeField(BRCodeId.ADDITIONAL_DATA_FIELD_TEMPLATE, additionalDataFieldTemplate.toString()))
                    .build();
            if (request.getValue() != null)
                brCode.setTransactionAmount(new BRCodeField(BRCodeId.TRANSACTION_AMOUNT, Utils.formatDecimal(request.getValue())));
            log.info("StaticQrCodeCreatorService :: QrCode Copia e cola gerado com sucesso - {}", brCode.toString());
            return brCode.toString();
    }

    private String getMerchantName(String nomeUsuarioFinal) {
        if(nomeUsuarioFinal.length() > 25) {
            nomeUsuarioFinal = nomeUsuarioFinal.substring(0, 25);
        }
        return nomeUsuarioFinal;
    }

    private String retrieveTransactionId(String transactionId) {
        if (transactionId != null)
            return transactionId.replaceAll("[^A-z0-9]", "");
        return "***";
    }
}
