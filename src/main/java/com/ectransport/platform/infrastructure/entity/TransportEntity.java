package com.ectransport.platform.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "transport",schema = "services")
public class TransportEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;
  @Column(name = "name")
  private String name;
  @Column(name = "code")
  private String code;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_service_type", referencedColumnName = "id_service_type", nullable = false)
  private ServiceTypeEntity serviceType;
}
