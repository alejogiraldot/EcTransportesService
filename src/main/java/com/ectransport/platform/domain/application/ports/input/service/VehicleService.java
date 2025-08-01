package com.ectransport.platform.domain.application.ports.input.service;

import com.ectransport.platform.domain.application.dto.VehicleBrandDto;
import com.ectransport.platform.domain.application.dto.VehicleDto;

import java.util.List;

public interface VehicleService {
  List<VehicleBrandDto> findAllVehiclesBrand();
  List<VehicleDto> findVehiclesById(Integer id);
}
