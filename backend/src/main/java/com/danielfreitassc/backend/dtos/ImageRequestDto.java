package com.danielfreitassc.backend.dtos;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ImageRequestDto(
    @NotBlank(message = "O campo nome não pode estar em branco")
    String name,
    @NotBlank(message = "O campo descrição não pode estar em branco")
    String description,
    @NotNull(message = "O campo foto não pode ser nulo")
    MultipartFile multipartFile
) {
    
}
