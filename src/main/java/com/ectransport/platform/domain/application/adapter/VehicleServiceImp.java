package com.ectransport.platform.domain.application.adapter;

import com.ectransport.platform.domain.application.dto.*;
import com.ectransport.platform.domain.application.mapper.VehicleApplicationMapper;
import com.ectransport.platform.domain.application.ports.input.service.VehicleService;
import com.ectransport.platform.domain.application.ports.output.service.VehicleRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImp implements VehicleService {
  private final VehicleRequestRepository vehicleRequestRepository;
  private final VehicleApplicationMapper vehicleApplicationMapper;

  public VehicleServiceImp(VehicleRequestRepository vehicleRequestRepository, VehicleApplicationMapper vehicleApplicationMapper) {
    this.vehicleRequestRepository = vehicleRequestRepository;
    this.vehicleApplicationMapper = vehicleApplicationMapper;
  }

  @Override
  public List<VehicleBrandDto> findAllVehiclesBrand() {
    return vehicleRequestRepository.findAllVehicleBrand().stream().map(vehicleApplicationMapper::vehicleBrandToVehicleBrandDto).toList();
  }

  @Override
  public List<VehicleDto> findAllVehhicle(String status) {
    if (status != null && !status.isEmpty()) {
      return vehicleRequestRepository.findAllVehhicleByStatus(status).stream().map(vehicleApplicationMapper::vehicleToVehicleDto).toList();
    }else{
      return vehicleRequestRepository.findAllVehhicle().stream().map(vehicleApplicationMapper::vehicleToVehicleDto).toList();
    }
  }

  @Override
  public VehicleDto createVehicle(CreateVehicleDto createVehicleDto) {
    return vehicleApplicationMapper.vehicleToVehicleDto(vehicleRequestRepository.createVehicle(createVehicleDto));
  }

  @Override
  public VehicleDto updateVehicle(UpdateVehicleDto updateVehicleDto) {
    return vehicleApplicationMapper.vehicleToVehicleDto(vehicleRequestRepository.updateVehicle(updateVehicleDto));
  }

  @Override
  public void updateVehicleStatus(UpdateStatusVehicleDto updateStatusVehicleDto) {
    Optional.ofNullable(vehicleRequestRepository.updateStatus(updateStatusVehicleDto))
        .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con ID: " + updateStatusVehicleDto.getId()));
  }
}
