package com.ectransport.platform.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@AllArgsConstructor
@Builder
public class FileUploadResponseDto {
  private String fileName;
  private String route;
  private LocalDateTime uploadDate;
  private UUID fkService;
}
