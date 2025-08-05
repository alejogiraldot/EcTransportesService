package com.ectransport.platform.infrastructure.repository;

import com.ectransport.platform.infrastructure.entity.ServiceOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ServiceJpaRepository extends JpaRepository<ServiceOrderEntity, UUID> {
  @Query(value = "SELECT * FROM services.services_orders", nativeQuery = true)
  List<ServiceOrderEntity> findServiceByUser(
      @Param("clientId") Integer clientId,
      @Param("initialDate") LocalTime initialDate,
      @Param("finalDate") LocalTime finalDate,
      @Param("plate") String plate
  );
}
