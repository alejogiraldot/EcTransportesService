package com.ectransport.platform.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "service_event_log", schema = "traceability")
public class ServiceEventLog {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "fk_service", nullable = false)
  private UUID fkService;

  @Column(name = "fk_event_type", nullable = false)
  private Integer eventType;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "event_payload", nullable = false)
  private String eventPayload;

  @Column(name = "created_by", length = 100)
  private String createdBy;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "commit", length = 500, columnDefinition = "VARCHAR(500) DEFAULT ''")
  @Builder.Default
  private String commit = "";
}