package com.praxis.qrcode.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.time.format.DateTimeFormatter;
import java.util.Random;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class TransactionUtils {

    public static String gerarIdTransacao(int tamanho) {
        String transactionId = DateTimeUtils.nowToTime().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")) + new Random().nextInt(999999999);
        return transactionId.length() < tamanho ? StringUtils.rightPad(transactionId, tamanho, "0") : transactionId;
    }
}
