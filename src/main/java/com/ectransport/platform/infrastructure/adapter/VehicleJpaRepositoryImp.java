package com.ectransport.platform.infrastructure.adapter;

import com.ectransport.platform.domain.application.dto.CreateVehicleDto;
import com.ectransport.platform.domain.application.dto.UpdateStatusVehicleDto;
import com.ectransport.platform.domain.application.dto.UpdateVehicleDto;
import com.ectransport.platform.domain.application.ports.output.service.VehicleRequestRepository;
import com.ectransport.platform.domain.core.entity.Vehicle;
import com.ectransport.platform.domain.core.entity.VehicleBrand;
import com.ectransport.platform.infrastructure.entity.VehicleEntity;
import com.ectransport.platform.infrastructure.mapper.VehicleInfrastructureMapper;
import com.ectransport.platform.infrastructure.repository.VehicleBrandJpaRepository;
import com.ectransport.platform.infrastructure.repository.VehicleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VehicleJpaRepositoryImp implements VehicleRequestRepository {

  private final VehicleBrandJpaRepository vehicleBrandJpaRepository;
  private final VehicleInfrastructureMapper vehicleInfrastructureMapper;
  private final VehicleJpaRepository vehicleJpaRepository;

  public VehicleJpaRepositoryImp(VehicleBrandJpaRepository vehicleBrandJpaRepository, VehicleInfrastructureMapper vehicleInfrastructureMapper, VehicleJpaRepository vehicleJpaRepository) {
    this.vehicleBrandJpaRepository = vehicleBrandJpaRepository;
    this.vehicleInfrastructureMapper = vehicleInfrastructureMapper;
    this.vehicleJpaRepository = vehicleJpaRepository;
  }


  @Override
  public List<VehicleBrand> findAllVehicleBrand() {
    return vehicleBrandJpaRepository.findAll().stream().map(vehicleInfrastructureMapper::vehicleBrandEntityToVehicleBrand).toList();
  }

  @Override
  public List<Vehicle> findAllVehhicle() {
    return vehicleJpaRepository.findAll().stream().map(vehicleInfrastructureMapper::vehicleEntityToVehicle).toList();
  }

  @Override
  public Vehicle createVehicle(CreateVehicleDto createVehicleDto) {
    VehicleEntity vehicleEntity = vehicleInfrastructureMapper.createVehicleDtoToVehicleEntity(createVehicleDto);
    return vehicleInfrastructureMapper.vehicleEntityToVehicle(vehicleJpaRepository.save(vehicleEntity));
  }

  @Override
  public Vehicle updateVehicle(UpdateVehicleDto updateVehicleDto) {
    VehicleEntity existingVehicle = vehicleJpaRepository.findById(updateVehicleDto.getId())
        .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con ID: " + updateVehicleDto.getId()));
    existingVehicle.setName(updateVehicleDto.getName().toUpperCase());
    existingVehicle.setPlate(updateVehicleDto.getPlate().toUpperCase());
    existingVehicle.setOwnershipStatus(updateVehicleDto.getOwnerShipStatus());
    existingVehicle.setVehicleBrandEntity(
        vehicleInfrastructureMapper.vehicleBrandDtoToVehicleEntity(updateVehicleDto.getVehicleBrandDto())
    );
    VehicleEntity updatedVehicle = vehicleJpaRepository.save(existingVehicle);
    return vehicleInfrastructureMapper.vehicleEntityToVehicle(updatedVehicle);
  }

  @Override
  public Vehicle updateStatus(UpdateStatusVehicleDto updateStatusVehicleDto) {
    VehicleEntity vehicleEntity = vehicleJpaRepository.findById(updateStatusVehicleDto.getId())
        .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con ID: " + updateStatusVehicleDto.getId()));
    vehicleEntity.setStatus(updateStatusVehicleDto.getStatus());
    return vehicleInfrastructureMapper.vehicleEntityToVehicle(vehicleJpaRepository.save(vehicleEntity));
  }

  @Override
  public List<Vehicle> findAllVehhicleByStatus(String status) {
    return vehicleJpaRepository.findByStatus(status).stream()
        .map(vehicleInfrastructureMapper::vehicleEntityToVehicle)
        .toList();
  }
}
