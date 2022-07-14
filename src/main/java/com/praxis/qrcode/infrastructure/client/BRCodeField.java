package com.praxis.qrcode.infrastructure.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class BRCodeField {
    private final String id;
    private String value;

    @Override
    public String toString() {
        if (value == null || value.isEmpty())
            return "";
        StringBuilder builder = new StringBuilder(id);
        builder.append(value.length());
        while (builder.length() < 4)
            builder.insert(2, 0);
        builder.append(value);
        return builder.toString();
    }
}
