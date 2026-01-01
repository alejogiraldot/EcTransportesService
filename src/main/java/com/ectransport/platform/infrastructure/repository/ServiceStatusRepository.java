package com.ectransport.platform.infrastructure.repository;

import com.ectransport.platform.infrastructure.entity.ServiceStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceStatusRepository extends JpaRepository<ServiceStatusEntity, Integer> {
}
