package com.ectransport.platform.domain.application.adapter;

import com.ectransport.platform.domain.application.dto.VehicleBrandDto;
import com.ectransport.platform.domain.application.dto.VehicleDto;
import com.ectransport.platform.domain.application.mapper.VehicleApplicationMapper;
import com.ectransport.platform.domain.application.ports.input.service.VehicleService;
import com.ectransport.platform.domain.application.ports.output.service.VehicleRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
  public List<VehicleDto> findVehiclesById(Integer id) {
    return vehicleRequestRepository.findVehicleById(id).stream().map(vehicleApplicationMapper::vehicleToVehicleDto).toList();
  }
}
