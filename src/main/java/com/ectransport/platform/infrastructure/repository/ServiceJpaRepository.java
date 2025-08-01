package com.ectransport.platform.infrastructure.repository;

import com.ectransport.platform.infrastructure.entity.ServiceOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ServiceJpaRepository extends JpaRepository<ServiceOrderEntity, UUID> {
}
