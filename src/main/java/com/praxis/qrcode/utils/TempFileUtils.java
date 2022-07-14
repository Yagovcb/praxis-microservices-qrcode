package com.praxis.qrcode.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TempFileUtils {

    public static String getFilePath(String fileName) {
        return getTempFilePath() + fileName;
    }

    @SneakyThrows(IOException.class)
    public static String getTempFilePath() {
        File temp = File.createTempFile("temp-file-name" + new Random().nextLong(), ".tmp");
        String absolutePath = temp.getAbsolutePath();
        return absolutePath.substring(0, absolutePath.lastIndexOf(File.separator) + 1);
    }

    public static String createNewFileName(String extension) {
        String dateTimePart = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssS"));
        int integerPart = Math.abs(new Random().nextInt() % 1000);
        return dateTimePart + "_" + integerPart + extension;
    }
}
