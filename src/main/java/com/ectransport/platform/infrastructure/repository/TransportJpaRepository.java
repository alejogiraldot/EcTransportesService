package com.ectransport.platform.infrastructure.repository;

import com.ectransport.platform.infrastructure.entity.TransportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportJpaRepository extends JpaRepository<TransportEntity, Integer> {
  List<TransportEntity> findByServiceType_Id(Integer idServiceType);
}
