package com.ectransport.platform.domain.application.mapper;

import com.ectransport.platform.domain.application.dto.FileUploadResponseDto;
import com.ectransport.platform.domain.core.entity.FileUpload;
import org.springframework.stereotype.Component;

@Component
public class UploadApplicationMapper {

  public FileUploadResponseDto fileUploadToFileUploadResponseDto(FileUpload fileUpload) {
    return FileUploadResponseDto.builder()
        .uploadDate(fileUpload.getUploadDate())
        .route(fileUpload.getRoute())
        .fileName(fileUpload.getFileName())
        .build();
  }
}
