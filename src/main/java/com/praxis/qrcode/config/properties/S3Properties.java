package com.praxis.qrcode.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "s3")
public class S3Properties {

    private String accessKeyId;
    private String secretKeyId;
    private String region;
    private String bucket;
}
