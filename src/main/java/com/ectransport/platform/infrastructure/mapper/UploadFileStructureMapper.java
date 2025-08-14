package com.ectransport.platform.infrastructure.mapper;

import com.ectransport.platform.domain.application.dto.MediaManagerResponseDto;
import com.ectransport.platform.domain.core.entity.FileUpload;
import com.ectransport.platform.infrastructure.entity.UploadServiceEntity;
import org.springframework.stereotype.Component;

@Component
public class UploadFileStructureMapper {


  public UploadServiceEntity fileUploadResponseDtoToUploadServiceEntity(MediaManagerResponseDto mediaManagerResponseDto) {
    return UploadServiceEntity.builder()
        .uploadDate(mediaManagerResponseDto.getUploadDate())
        .fileName(mediaManagerResponseDto.getFileName())
        .contentType(mediaManagerResponseDto.getContentType())
        .fileSize(mediaManagerResponseDto.getFileSize())
        .route(mediaManagerResponseDto.getRoute())
        .fkService(mediaManagerResponseDto.getFkService())
        .build();
  }

  public FileUpload uploadServiceEntityToFileUploaded(UploadServiceEntity uploadServiceEntity) {
    return FileUpload.builder()
        .uploadDate(uploadServiceEntity.getUploadDate())
        .fileName(uploadServiceEntity.getFileName())
        .fileSize(uploadServiceEntity.getFileSize())
        .route(uploadServiceEntity.getRoute())
        .build();
  }
}
