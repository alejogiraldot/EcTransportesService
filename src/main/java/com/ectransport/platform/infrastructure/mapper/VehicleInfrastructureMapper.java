package com.ectransport.platform.infrastructure.mapper;

import com.ectransport.platform.domain.application.dto.CreateVehicleDto;
import com.ectransport.platform.domain.application.dto.VehicleBrandDto;
import com.ectransport.platform.domain.core.entity.Vehicle;
import com.ectransport.platform.domain.core.entity.VehicleBrand;
import com.ectransport.platform.infrastructure.entity.VehicleBrandEntity;
import com.ectransport.platform.infrastructure.entity.VehicleEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
        .status(vehicleEntity.getStatus())
        .build();
  }

  public VehicleEntity createVehicleDtoToVehicleEntity(CreateVehicleDto createVehicleDto) {
    return VehicleEntity.builder()
        .createdAt(LocalDateTime.now())
        .insuranceExpiration(null)
        .name(createVehicleDto.getName())
        .ownershipStatus(createVehicleDto.getOwnerShipStatus())
        .plate(createVehicleDto.getPlate())
        .vehicleBrandEntity(vehicleBrandDtoToVehicleEntity(createVehicleDto.getVehicleBrandDto()))
        .build();
  }

  public VehicleBrandEntity vehicleBrandDtoToVehicleEntity(VehicleBrandDto vehicleBrandDto) {
    return VehicleBrandEntity.builder()
        .name(vehicleBrandDto.getName())
        .id(vehicleBrandDto.getId())
        .createdAt(vehicleBrandDto.getCreationDate())
        .build();
  }
}
