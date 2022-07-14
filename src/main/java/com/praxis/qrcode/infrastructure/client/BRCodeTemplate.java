package com.praxis.qrcode.infrastructure.client;

import com.praxis.qrcode.utils.CRC16;
import com.praxis.qrcode.utils.Utils;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BRCodeTemplate {
    private final BRCodeField payloadFormatIndicator = new BRCodeField(BRCodeId.PAYLOAD_FORMAT_INDICATOR, "01");
    private final BRCodeField countryCode = new BRCodeField(BRCodeId.COUNTRY_CODE, "BR");
    private final BRCodeField uniquePaymentCode = new BRCodeField(BRCodeId.INDICATOR_UNIQUE_PAYMENT, "12");
    private BRCodeField merchantAccountInformation;
    private boolean uniquePayment;
    private boolean dinamicQRCode;
    private BRCodeField merchantCategoryCode;
    private BRCodeField transactionCurrency;
    private BRCodeField transactionAmount;
    private BRCodeField merchantName;
    private BRCodeField merchantCity;
    private BRCodeField additionalDataFieldTemplate;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder()
                .append(payloadFormatIndicator);
        if (uniquePayment)
            builder.append(uniquePaymentCode);
        builder.append(merchantAccountInformation)
                .append(merchantCategoryCode)
                .append(transactionCurrency);
        if (transactionAmount != null)
            builder.append(transactionAmount);
        builder.append(countryCode)
                .append(merchantName)
                .append(merchantCity)
                .append(additionalDataFieldTemplate)
                .append(BRCodeId.CRC16 + "04"); //id e tamanho do crc16 conforme manual
        String crc16 = CRC16.calculate(builder.toString());
        builder.append(Utils.leftPadWithZero(crc16.replaceAll("0x", ""), 4));
        return builder.toString();
    }
}
