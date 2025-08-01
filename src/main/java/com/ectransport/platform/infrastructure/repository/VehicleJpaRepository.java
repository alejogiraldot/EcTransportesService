package com.ectransport.platform.infrastructure.repository;

import com.ectransport.platform.infrastructure.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleJpaRepository extends JpaRepository<VehicleEntity, Integer> {
  List<VehicleEntity> findByVehicleBrandEntity_Id(Integer brandId);
}
