package com.ectransport.platform.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class FileInfoByServiceDto {
  private String fileName;
  private String route;
  private String presignedUrl;
  private String contentType;
  private LocalDateTime uploadDate;
  private String beeper;
  private String paymentType;
  private String description;
  private String amount;
  private Integer fkTypeUpload;

}
