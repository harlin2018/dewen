package com.dewen.project.constants;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "file.param")
public class FileParamConstant {
    private String endpoint;
    private String bucketName;
    private String accessKeyId;
    private String accessKeySecret;
}
