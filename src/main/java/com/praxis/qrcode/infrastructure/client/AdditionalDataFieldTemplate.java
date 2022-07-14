package com.praxis.qrcode.infrastructure.client;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdditionalDataFieldTemplate {
    private BRCodeField txId;

    @Override
    public String toString() {
        return String.valueOf(txId);
    }
}
