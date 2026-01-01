package com.ectransport.platform.infrastructure.repository;

import com.ectransport.platform.domain.application.dto.ServiceRequirementId;
import com.ectransport.platform.infrastructure.entity.ServiceRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface ServiceRequirementJpaRepository extends JpaRepository<ServiceRequirement, ServiceRequirementId> {
  @Modifying(clearAutomatically = true, flushAutomatically = true)  // ✅ Agregar flushAutomatically
  @Transactional
  @Query("DELETE FROM ServiceRequirement sr WHERE sr.idService = :idService")
  void deleteByIdService(@Param("idService") UUID idService);

  @Query("SELECT sr FROM ServiceRequirement sr WHERE sr.idService = :idService")
  List<ServiceRequirement> findByIdService(@Param("idService") UUID idService);
}

