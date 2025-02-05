package com.danielfreitassc.backend.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielfreitassc.backend.dtos.ImageRequestDto;
import com.danielfreitassc.backend.dtos.ImageResponseDto;
import com.danielfreitassc.backend.services.UploadService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class UploadController {
    private final UploadService uploadService;

    @PostMapping
    public ImageResponseDto upload(@ModelAttribute @Valid ImageRequestDto imageDTO) throws Exception {
        return uploadService.upload(imageDTO);
    } 

    @GetMapping
    public List<ImageResponseDto> getAll() {
        return uploadService.getAll();
    }

    @GetMapping("/{id}")
    public ImageResponseDto getById(@PathVariable String id) {
        return uploadService.getById(id);
    }

    @PutMapping("/{id}")
    public ImageResponseDto update(@PathVariable String id,@ModelAttribute @Valid ImageRequestDto imageRequestDto) throws Exception {
        return uploadService.update(id, imageRequestDto);
    }

    @DeleteMapping("/{id}")
    public ImageResponseDto delete(@PathVariable String id) {
        return uploadService.delete(id);
    }

    @GetMapping(value = "/{objectId}/photo", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@PathVariable String objectId) throws Exception {
        return uploadService.getImage(objectId);
    }
}
