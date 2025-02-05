package com.danielfreitassc.backend.services;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.danielfreitassc.backend.dtos.ImageRequestDto;
import com.danielfreitassc.backend.dtos.ImageResponseDto;
import com.danielfreitassc.backend.mappers.ImageMapper;
import com.danielfreitassc.backend.models.ImageEntity;
import com.danielfreitassc.backend.repositories.ImageRepository;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UploadService {
    private final MinioClient minioClient;
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    public ImageResponseDto upload(ImageRequestDto imageDTO) throws Exception {
        String objectId = UUID.randomUUID().toString();

        minioClient.putObject(
            PutObjectArgs.builder()
                .bucket("images")
                .object(objectId)
                .stream(imageDTO.multipartFile().getInputStream(), imageDTO.multipartFile().getSize(), -1)
                .contentType(imageDTO.multipartFile().getContentType())
                .build()
        );

        ImageEntity imageEntity = imageMapper.toEntity(imageDTO);
        imageEntity.setObjectId(objectId); 

        imageRepository.save(imageEntity);

        return imageMapper.toResponse(imageEntity);
    }
    
    public List<ImageResponseDto> getAll() {
        return imageRepository.findAll().stream().map(imageMapper::toResponse).toList();
    }

    public ImageResponseDto getById(String id) {
       ImageEntity image = checkId(id);
        return imageMapper.toResponse(image);
    }

    public ImageResponseDto delete(String id) {
        ImageEntity image = checkId(id);


        try {
            minioClient.removeObject(
                RemoveObjectArgs.builder()
                .bucket("images")
                .object(image.getObjectId())
                .build()
            );

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Erro ao excluir imagem",e);
        }

        imageRepository.delete(image);
        return imageMapper.toResponse(image);
    }

    public ImageResponseDto update(String id, ImageRequestDto imageRequestDto) throws Exception {
        ImageEntity image = checkId(id);

        ImageEntity existinImageEntity = image;
        ImageEntity imageEntity = imageMapper.toEntity(imageRequestDto);

        imageEntity.setId(id);
        imageEntity.setObjectId(existinImageEntity.getObjectId());

        if(imageRequestDto != null) {
            String objectId = existinImageEntity.getObjectId();

            minioClient.putObject(
                PutObjectArgs.builder()
                .bucket("images")
                .object(objectId)
                .stream(
                    imageRequestDto.multipartFile().getInputStream(),
                    imageRequestDto.multipartFile().getSize(), -1
                    ).contentType(imageRequestDto.multipartFile().getContentType())
                .build()
            );
        }

        imageRepository.save(imageEntity);
        return imageMapper.toResponse(imageEntity);
    }

    public byte[] getImage(String objectId) throws Exception {
        try (InputStream stream = minioClient.getObject(
            GetObjectArgs.builder().bucket("images").object(objectId).build())) {

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;

            while ((length = stream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, length);
            }

            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Imagem não encontrada");
        }
    }

    public ImageEntity checkId(String id)  {
        Optional<ImageEntity> image = imageRepository.findById(id);
        if(image.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Informações da imagem encontrada");
        return image.get();
    }

}
