package com.ectransport.platform.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class FileByServiceDto {
  private CompanyDto companyDto;
  private String fileName;
  private String route;
  private String beeper;
  private String paymentType;
  private String description;
  private String amount;
  private Integer fkTypeUpload;
  private String legalName;
  private Integer clientType;
  private String plate;
  private Integer serviceAmount;
  private String driver;
  private UUID idFile;
}

