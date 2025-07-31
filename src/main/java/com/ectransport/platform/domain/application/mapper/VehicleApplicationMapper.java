package com.ectransport.platform.domain.application.mapper;

import com.ectransport.platform.domain.application.dto.VehicleBrandDto;
import com.ectransport.platform.domain.core.entity.VehicleBrand;
import org.springframework.stereotype.Component;

@Component
public class VehicleApplicationMapper {
  public VehicleBrandDto vehicleBrandToVehicleBrandDto(VehicleBrand vehicleBrand) {
    return VehicleBrandDto.builder()
        .id(vehicleBrand.getId())
        .name(vehicleBrand.getName())
        .build();
  }
}
