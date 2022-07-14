package com.praxis.qrcode.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.Map;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageUtils {

    public static void fill(BufferedImage image, String content, int imageSize) {
        BitMatrix bitMatrix = createBitMatrix(content, imageSize);
        Graphics2D graphics = createGraphics(image, imageSize);
        for (int xCoordinate = 0; xCoordinate < imageSize; xCoordinate++) {
            for (int yCoordinate = 0; yCoordinate < imageSize; yCoordinate++) {
                if (bitMatrix.get(xCoordinate, yCoordinate))
                    graphics.fillRect(xCoordinate, yCoordinate, 1, 1);
            }
        }
    }

    @SneakyThrows(WriterException.class)
    static BitMatrix createBitMatrix(String content, int imageSize) {
        Map<EncodeHintType, Object> hintType = new EnumMap<>(EncodeHintType.class);
        hintType.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hintType.put(EncodeHintType.MARGIN, 1);
        hintType.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        return new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, imageSize, imageSize, hintType);
    }

    static Graphics2D createGraphics(BufferedImage image, int imageSize) {
        image.createGraphics();
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, imageSize, imageSize);
        graphics.setColor(Color.BLUE);
        return graphics;
    }
}
