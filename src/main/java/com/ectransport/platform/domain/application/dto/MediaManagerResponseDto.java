package com.ectransport.platform.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class MediaManagerResponseDto {
  private String fileName;
  private String route;
  private String contentType;
  private Long fileSize;
  private LocalDateTime uploadDate;
  private UUID fkService;
  private String paymentType;
  private Integer amount;
  private Integer beeper;
  private String description;
  private Integer fkTypeUpload;
  private String presignedUrl;
}
