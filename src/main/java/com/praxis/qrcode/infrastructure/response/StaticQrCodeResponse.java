package com.praxis.qrcode.infrastructure.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaticQrCodeResponse {
                    //EMV
    private String qrCode;
    private String imagemUrl;
    private String txId;
    private String transactionDateTime;
}
