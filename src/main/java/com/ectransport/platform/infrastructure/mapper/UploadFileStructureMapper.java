package com.ectransport.platform.infrastructure.mapper;

import com.ectransport.platform.domain.application.dto.FileByServiceDto;
import com.ectransport.platform.domain.application.dto.MediaManagerResponseDto;
import com.ectransport.platform.domain.application.ports.repository.UploadDataService;
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
        .amount(mediaManagerResponseDto.getAmount())
        .beeper(mediaManagerResponseDto.getBeeper())
        .description(mediaManagerResponseDto.getDescription())
        .paymentType(mediaManagerResponseDto.getPaymentType())
        .fkTypeUpload(mediaManagerResponseDto.getFkTypeUpload())
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

  public FileByServiceDto uploadDataServiceToFileByServiceDto(UploadDataService fileUpload) {
    return FileByServiceDto.builder()
        .fkTypeUpload(fileUpload.getFkTypeUpload())
        .amount(fileUpload.getAmount())
        .beeper(fileUpload.getBeeper())
        .description(fileUpload.getDescription())
        .fileName(fileUpload.getFileName())
        .paymentType(fileUpload.getPaymentType())
        .route(fileUpload.getRoute())
        .build();
  }
}
