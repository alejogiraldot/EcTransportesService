package com.ectransport.platform.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "service_status", schema = "services")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceStatusEntity {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "id_status")
  private Integer idStatus;
}