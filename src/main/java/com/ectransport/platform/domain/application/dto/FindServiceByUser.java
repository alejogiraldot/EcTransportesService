package com.ectransport.platform.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class FindServiceByUser {
  private LocalDate initialDate;
  private LocalDate finalDate;
  private UUID clientId;
  private String plate;
  private Integer userId;
  private Integer status;
  private Integer driverId;
  private String reference;
  private String serviceNumber;
}
