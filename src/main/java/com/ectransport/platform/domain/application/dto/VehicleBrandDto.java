package com.ectransport.platform.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class VehicleBrandDto {
  private Long id;
  private String name;
  private LocalDateTime creationDate;
}
