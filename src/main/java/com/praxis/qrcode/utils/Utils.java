package com.praxis.qrcode.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class Utils {

    public static final String ZERO ="000";

    public static String leftPadWithZero(String value, Integer size) {
        return StringUtils.leftPad(value, size, "0");
    }

    public static String formatDecimal(BigDecimal value) {
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.ENGLISH);
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat.setGroupingUsed(false);
        return decimalFormat.format(value);
    }
}
