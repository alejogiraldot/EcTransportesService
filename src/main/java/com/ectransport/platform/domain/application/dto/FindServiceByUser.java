package com.ectransport.platform.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@Builder
public class FindServiceByUser {
  private LocalTime initialDate;
  private LocalTime finalDate;
  private Integer clientId;
  private String plate;
}
