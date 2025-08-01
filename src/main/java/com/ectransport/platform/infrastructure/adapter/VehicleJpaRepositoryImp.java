package com.ectransport.platform.infrastructure.adapter;

import com.ectransport.platform.domain.application.ports.output.service.VehicleRequestRepository;
import com.ectransport.platform.domain.core.entity.Vehicle;
import com.ectransport.platform.domain.core.entity.VehicleBrand;
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
  public List<Vehicle> findVehicleById(Integer id) {
    return vehicleJpaRepository.findByVehicleBrandEntity_Id(id).stream().map(vehicleInfrastructureMapper::vehicleEntityToVehicle).toList();
  }
}
