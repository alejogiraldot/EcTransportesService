package com.ectransport.platform.infrastructure.repository;

import com.ectransport.platform.infrastructure.entity.VehicleBrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleBrandJpaRepository extends JpaRepository<VehicleBrandEntity, Integer> {
}
