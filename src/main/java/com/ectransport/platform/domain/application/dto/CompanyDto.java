package com.ectransport.platform.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CompanyDto {
  private String companyName;
  private String clientType;
  private String plate;
  private String serviceValue;
}
