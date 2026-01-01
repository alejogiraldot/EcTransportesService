package com.ectransport.platform.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "event_type", schema = "traceability")
public class EventType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_event_type")
  private Integer idEventType;

  @Column(name = "code", nullable = false, unique = true, length = 50)
  private String code;

  @Column(name = "description", nullable = false, length = 200)
  private String description;

  @Column(name = "active", nullable = false)
  private Boolean active;
}