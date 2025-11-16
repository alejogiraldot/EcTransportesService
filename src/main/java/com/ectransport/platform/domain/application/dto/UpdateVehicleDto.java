package com.ectransport.platform.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UpdateVehicleDto {
  private Integer id;
  private String name;
  private String plate;
  private String ownerShipStatus;
  private VehicleBrandDto vehicleBrandDto;
}
