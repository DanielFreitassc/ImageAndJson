package com.danielfreitassc.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.danielfreitassc.backend.dtos.ImageRequestDto;
import com.danielfreitassc.backend.dtos.ImageResponseDto;
import com.danielfreitassc.backend.models.ImageEntity;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageResponseDto toResponse(ImageEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "objectId", ignore = true)
    ImageEntity toEntity(ImageRequestDto dto);
}
