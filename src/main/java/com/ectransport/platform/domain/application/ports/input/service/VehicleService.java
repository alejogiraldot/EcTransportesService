package com.ectransport.platform.domain.application.ports.input.service;

import com.ectransport.platform.domain.application.dto.VehicleBrandDto;

import java.util.List;

public interface VehicleService {
  List<VehicleBrandDto> findAllVehiclesBrand();
}
