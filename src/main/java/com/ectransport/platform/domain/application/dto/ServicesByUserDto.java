package com.ectransport.platform.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Data
public class ServicesByUserDto {
  private LocalTime serviceHour;
  private LocalDate serviceDate;
  private String origin;
  private String destination;
  private UUID idService;
}
