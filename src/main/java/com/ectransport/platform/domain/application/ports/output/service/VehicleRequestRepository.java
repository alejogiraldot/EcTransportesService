package com.ectransport.platform.domain.application.ports.output.service;

import com.ectransport.platform.domain.core.entity.Vehicle;
import com.ectransport.platform.domain.core.entity.VehicleBrand;

import java.util.List;

public interface VehicleRequestRepository {
  List<VehicleBrand> findAllVehicleBrand();
  List<Vehicle> findVehicleById(Integer id);
}
