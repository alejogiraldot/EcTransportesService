package com.ectransport.platform.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class FindServiceByUser {
  private LocalDate initialDate;
  private LocalDate finalDate;
  private Integer clientId;
  private String plate;
}
