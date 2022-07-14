package com.praxis.qrcode.application.service;

import com.praxis.qrcode.utils.ImageUtils;
import com.praxis.qrcode.utils.TempFileUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class QrCodeImageGeneratorService {

    private static final int IMAGE_SIZE = 300;

    private final S3Service s3Service;

    @SneakyThrows(value = IOException.class)
    public String generate(String content) {
        log.info("QrCodeImageGeneratorService :: Iniciando a geração da imagem do QRCode");
        String filePath = TempFileUtils.getFilePath(TempFileUtils.createNewFileName(".png"));
        File file = new File(Objects.requireNonNull(filePath));
        BufferedImage image = new BufferedImage(IMAGE_SIZE, IMAGE_SIZE, BufferedImage.TYPE_INT_RGB);
        ImageUtils.fill(image, content, IMAGE_SIZE);
        ImageIO.write(image, "png", file);
        String url = s3Service.saveS3Bucket(file, "qr-code");
        log.info("QrCodeImageGeneratorService :: Imagem criada com sucesso {}", url);
        return url;
    }


}
