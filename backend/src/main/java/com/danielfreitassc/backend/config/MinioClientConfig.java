package com.danielfreitassc.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.minio.MinioClient;

@Configuration
public class MinioClientConfig {
    @Bean
    MinioClient minioClient() {
        return MinioClient.builder().endpoint("http://localhost:9000").credentials("Sqmfs6dH3ThPy8ICbkB1", "Yfr4UjgMguyoKiLCGaEb9jV9QgNujHfHPjcUuny2").build();
    }
}
