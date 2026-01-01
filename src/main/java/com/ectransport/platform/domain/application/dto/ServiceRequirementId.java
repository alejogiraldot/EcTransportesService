  package com.ectransport.platform.domain.application.dto;

  import lombok.*;

  import java.io.Serializable; // NUEVO
  import java.util.UUID;

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @EqualsAndHashCode
  public class ServiceRequirementId implements Serializable { // NUEVO: implements Serializable

    private UUID idService;
    private Integer idRequirement;
  }
