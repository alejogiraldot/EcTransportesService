package com.ectransport.platform.infrastructure.mapper;

import com.ectransport.platform.domain.core.entity.VehicleBrand;
import com.ectransport.platform.infrastructure.entity.VehicleBrandEntity;
import org.springframework.stereotype.Component;

@Component
public class VehicleInfrastructureMapper {

  public VehicleBrand vehicleBrandEntityToVehicleBrand(VehicleBrandEntity vehicleBrandEntity) {
    return VehicleBrand.builder()
        .creationDate(vehicleBrandEntity.getCreatedAt())
        .id(vehicleBrandEntity.getId())
        .name(vehicleBrandEntity.getName())
        .build();
  }
}
