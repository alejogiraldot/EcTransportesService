package com.ectransport.platform.infrastructure.repository;

import com.ectransport.platform.domain.application.ports.repository.ServiceEventHistory;
import com.ectransport.platform.infrastructure.entity.ServiceEventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ServiceEventLogRepository extends JpaRepository<ServiceEventLog, Long> {

  @Query(value = """
      SELECT
        sel.id AS id,
        sel.fk_event_type AS eventType,
        et.code AS eventCode,
        sel.event_payload AS eventPayload,
        sel.created_by AS createdBy,
        sel.created_at AS createdAt,
        sel.commit AS commit,
        cl.trade_name AS tradeName
      FROM traceability.service_event_log sel
      JOIN traceability.event_type et ON et.id_event_type = sel.fk_event_type
      JOIN services.service_orders so ON so.id_service = sel.fk_service
      JOIN usermanagement.client cl ON cl.client_id = so.fk_client
      WHERE sel.fk_service = :serviceId
      ORDER BY sel.created_at ASC
      """, nativeQuery = true)
  List<ServiceEventHistory> findHistoryByFkService(@Param("serviceId") UUID fkService);
}