package com.ectransport.platform.infrastructure.mapper;

import com.ectransport.platform.domain.core.entity.Vehicle;
import com.ectransport.platform.domain.core.entity.VehicleBrand;
import com.ectransport.platform.infrastructure.entity.VehicleBrandEntity;
import com.ectransport.platform.infrastructure.entity.VehicleEntity;
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

  public Vehicle vehicleEntityToVehicle(VehicleEntity vehicleEntity) {
    return Vehicle.builder()
        .vehicleBrand(vehicleBrandEntityToVehicleBrand(vehicleEntity.getVehicleBrandEntity()))
        .id(vehicleEntity.getId())
        .name(vehicleEntity.getName())
        .ownerStatus(vehicleEntity.getOwnershipStatus())
        .plate(vehicleEntity.getPlate())
        .build();
  }
}
