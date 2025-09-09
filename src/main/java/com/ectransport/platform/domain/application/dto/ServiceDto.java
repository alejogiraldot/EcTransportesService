package com.ectransport.platform.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;


@Builder
@Data
@AllArgsConstructor
public class ServiceDto {
  private UUID idService;
  private String serviceType;
  private LocalDate serviceDate;
  private LocalTime hourService;
  private String brandVehicle;
  private String origin;
  private String destination;
  private Integer peopleNumber;
  private BigDecimal serviceAmmount;
  private String observations;
  private String userNumber;
  private String userName;
  private String userEmail;
  private String flightNumber;
  private String methodOfPayment;
  private String voucher;
  private String plate;
  private String serviceNumber;
  private String driverName;
  private String driverLastName;
  private String clientName;
  private Integer statusId;
  private Integer statusIdentifier;
  private Integer driverId;
  private String userIdentification;
  private Integer clientType;
}
