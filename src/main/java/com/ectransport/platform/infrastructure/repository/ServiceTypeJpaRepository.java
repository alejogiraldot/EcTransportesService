package com.ectransport.platform.infrastructure.repository;

import com.ectransport.platform.infrastructure.entity.ServiceTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceTypeJpaRepository extends JpaRepository<ServiceTypeEntity, Integer> {

}
