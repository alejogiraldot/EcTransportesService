package com.ectransport.platform.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "service_type",schema = "services")
public class ServiceTypeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_service_type")
  private Integer id;
  @Column(name="name")
  private String name;
  @Column(name="code")
  private String code;
}
