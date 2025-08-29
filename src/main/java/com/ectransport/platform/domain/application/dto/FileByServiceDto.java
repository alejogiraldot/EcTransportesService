package com.ectransport.platform.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FileByServiceDto {
  private String fileName;
  private String route;
  private String beeper;
  private String paymentType;
  private String description;
  private String amount;
  private Integer fkTypeUpload;

}

