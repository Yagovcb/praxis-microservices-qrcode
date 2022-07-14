package com.praxis.qrcode;

import com.praxis.qrcode.config.properties.S3Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {S3Properties.class})
public class PraxisMicroservicesQrcodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PraxisMicroservicesQrcodeApplication.class, args);
    }

}
