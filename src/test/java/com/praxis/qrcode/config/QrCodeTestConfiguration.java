package com.praxis.qrcode.config;

import com.praxis.qrcode.application.service.QrCodeEstaticoService;
import com.praxis.qrcode.application.service.QrCodeImageGeneratorService;
import com.praxis.qrcode.application.service.S3Service;
import com.praxis.qrcode.application.service.StaticQrCodeCreatorService;
import com.praxis.qrcode.config.properties.S3Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class QrCodeTestConfiguration {

    @Autowired
    private StaticQrCodeCreatorService staticQrCodeCreatorService;

    @Autowired
    private QrCodeImageGeneratorService qrCodeImageGeneratorService;


    @Bean
    public QrCodeEstaticoService qrCodeEstaticoService(){
        return new QrCodeEstaticoService(staticQrCodeCreatorService, qrCodeImageGeneratorService);
    }

    @Bean("s3properties")
    public S3Properties s3Properties(){
        return new S3Properties();
    }
    @Bean
    public S3Service s3Service(){
        return new S3Service(s3Properties());
    }

}
