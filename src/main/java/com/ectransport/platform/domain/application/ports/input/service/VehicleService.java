package com.ectransport.platform.domain.application.ports.input.service;

import com.ectransport.platform.domain.application.dto.*;

import java.util.List;

public interface VehicleService {
  List<VehicleBrandDto> findAllVehiclesBrand();
  List<VehicleDto> findAllVehhicle(String status);
  VehicleDto createVehicle(CreateVehicleDto createVehicleDto);
  VehicleDto updateVehicle(UpdateVehicleDto updateVehicleDto);
  void updateVehicleStatus(UpdateStatusVehicleDto updateStatusVehicleDto);
}
