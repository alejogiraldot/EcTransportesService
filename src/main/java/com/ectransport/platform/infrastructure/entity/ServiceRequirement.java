package com.ectransport.platform.infrastructure.entity;

import com.ectransport.platform.domain.application.dto.ServiceRequirementId;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "service_requirement", schema = "services")
@IdClass(ServiceRequirementId.class) // Ahora está en el mismo paquete
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceRequirement {

  @Id
  @Column(name = "id_service")
  private UUID idService;

  @Id
  @Column(name = "id_requirement")
  private Integer idRequirement;
}
