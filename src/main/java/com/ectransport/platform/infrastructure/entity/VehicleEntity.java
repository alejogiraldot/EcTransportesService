package com.ectransport.platform.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "vehicle", schema = "services")
public class VehicleEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name", length = 100, nullable = false)
  private String name;

  @ManyToOne
  @JoinColumn(name = "fk_brand_id", nullable = false)
  private VehicleBrandEntity vehicleBrandEntity;

  @Column(name = "plate", length = 20, nullable = false)
  private String plate;

  @Column(name = "insurance_expiration")
  private LocalDateTime insuranceExpiration;

  @Column(name = "revision_expiration")
  private LocalDateTime revisionExpiration;

  @Column(name = "ownership_status", columnDefinition = "text")
  private String ownershipStatus;

  @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime createdAt;

}
