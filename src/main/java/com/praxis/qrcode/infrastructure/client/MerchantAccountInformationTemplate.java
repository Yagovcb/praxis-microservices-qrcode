package com.praxis.qrcode.infrastructure.client;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MerchantAccountInformationTemplate {

    private final BRCodeField gui = new BRCodeField("00", "br.gov.bcb.pix");
    private boolean dinamico;
    private BRCodeField chave;
    private BRCodeField mensagem;
    private BRCodeField url;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder()
                .append(gui)
                .append(chave);
        if (mensagem != null) {
            prepareMessageBlock(builder.length());
            builder.append(mensagem);
        }

        if(dinamico){
            builder.append(url);
        }

        if (builder.length() > 99 && !dinamico)
            builder.substring(0, 99);

        return builder.toString();
    }

    private void prepareMessageBlock(Integer currentSize) {
        int maxMessageSize = 99 - (4 + currentSize);
        if (mensagem.getValue() != null && mensagem.getValue().length() > maxMessageSize)
            mensagem.setValue(mensagem.getValue().substring(0, maxMessageSize));
    }
}
