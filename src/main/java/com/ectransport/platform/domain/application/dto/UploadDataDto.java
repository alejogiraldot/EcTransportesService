package com.ectransport.platform.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UploadDataDto {
  private String paymentType;
  private String fileName;
  private String contentType;
  private Integer amount;
  private Integer beeper;
  private String description;
}
