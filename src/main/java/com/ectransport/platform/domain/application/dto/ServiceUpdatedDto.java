package com.ectransport.platform.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class ServiceUpdatedDto {
  private String plate;
  private Integer idDriver;
}
