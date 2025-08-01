package com.ectransport.platform.domain.application.mapper;

import com.ectransport.platform.domain.application.dto.VehicleBrandDto;
import com.ectransport.platform.domain.application.dto.VehicleDto;
import com.ectransport.platform.domain.core.entity.Vehicle;
import com.ectransport.platform.domain.core.entity.VehicleBrand;
import com.ectransport.platform.infrastructure.entity.VehicleEntity;
import org.springframework.stereotype.Component;

@Component
public class VehicleApplicationMapper {
  public VehicleBrandDto vehicleBrandToVehicleBrandDto(VehicleBrand vehicleBrand) {
    return VehicleBrandDto.builder()
        .id(vehicleBrand.getId())
        .name(vehicleBrand.getName())
        .build();
  }

  public VehicleDto vehicleToVehicleDto(Vehicle vehicle) {
    return VehicleDto.builder()
        .vehicleBrand(vehicleBrandToVehicleBrandDto(vehicle.getVehicleBrand()))
        .id(vehicle.getId())
        .name(vehicle.getName())
        .plate(vehicle.getPlate())
        .build();
  }
}
