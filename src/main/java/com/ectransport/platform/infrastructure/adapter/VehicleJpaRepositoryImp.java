package com.ectransport.platform.infrastructure.adapter;

import com.ectransport.platform.domain.application.ports.output.service.VehicleRequestRepository;
import com.ectransport.platform.domain.core.entity.VehicleBrand;
import com.ectransport.platform.infrastructure.mapper.VehicleInfrastructureMapper;
import com.ectransport.platform.infrastructure.repository.VehicleBrandJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VehicleJpaRepositoryImp implements VehicleRequestRepository {

  private final VehicleBrandJpaRepository vehicleBrandJpaRepository;
  private final VehicleInfrastructureMapper vehicleInfrastructureMapper;

  public VehicleJpaRepositoryImp(VehicleBrandJpaRepository vehicleBrandJpaRepository, VehicleInfrastructureMapper vehicleInfrastructureMapper) {
    this.vehicleBrandJpaRepository = vehicleBrandJpaRepository;
    this.vehicleInfrastructureMapper = vehicleInfrastructureMapper;
  }


  @Override
  public List<VehicleBrand> findAllVehicleBrand() {
    return vehicleBrandJpaRepository.findAll().stream().map(vehicleInfrastructureMapper::vehicleBrandEntityToVehicleBrand).toList();
  }
}
