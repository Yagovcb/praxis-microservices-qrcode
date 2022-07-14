package com.praxis.qrcode.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.time.ZoneId;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateTimeUtils {

    private static final ZoneId ZONE_ID = ZoneId.of("Brazil/East");

    public static LocalDateTime nowToTime() {
        return LocalDateTime.now(ZONE_ID);
    }
}
