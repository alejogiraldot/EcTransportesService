package com.ectransport.platform.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class UpdateDriverDto {
  private Integer driverId;
  private UUID serviceId;
  private String plate;
  private Integer idStatus;
}
