package com.ectransport.platform.domain.application.ports.output.service;

import com.ectransport.platform.domain.application.dto.CreateVehicleDto;
import com.ectransport.platform.domain.application.dto.UpdateStatusVehicleDto;
import com.ectransport.platform.domain.application.dto.UpdateVehicleDto;
import com.ectransport.platform.domain.core.entity.Vehicle;
import com.ectransport.platform.domain.core.entity.VehicleBrand;

import java.util.List;

public interface VehicleRequestRepository {
  List<VehicleBrand> findAllVehicleBrand();
  List<Vehicle> findAllVehhicle();
  Vehicle createVehicle(CreateVehicleDto createVehicleDto);
  Vehicle updateVehicle(UpdateVehicleDto updateVehicleDto);
  Vehicle updateStatus(UpdateStatusVehicleDto updateStatusVehicleDto);
  List<Vehicle> findAllVehhicleByStatus(String status);
}
