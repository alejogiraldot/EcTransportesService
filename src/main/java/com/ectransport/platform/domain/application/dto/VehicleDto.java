package com.ectransport.platform.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class VehicleDto {
  private Integer id;
  private String name;
  private String plate;
  private String ownerStatus;
  private VehicleBrandDto vehicleBrand;
}
