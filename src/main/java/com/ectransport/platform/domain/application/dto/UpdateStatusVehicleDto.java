package com.ectransport.platform.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UpdateStatusVehicleDto {
  private String status;
  private Integer id;
}
