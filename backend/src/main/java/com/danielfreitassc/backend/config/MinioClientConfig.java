package com.danielfreitassc.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.minio.MinioClient;

@Configuration
public class MinioClientConfig {
    @Bean
    MinioClient minioClient() {
        return MinioClient.builder().endpoint("http://localhost:9000").credentials("oueWCk42RZ7OB508YINk", "eQmtQrwJVSk3QxmBBg2kXX0Jm8fI4NjU99Hqr5Ul").build();
    }
}
