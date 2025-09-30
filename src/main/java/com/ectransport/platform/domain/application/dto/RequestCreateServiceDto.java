package com.ectransport.platform.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class RequestCreateServiceDto {
  private UUID idService;
  private String serviceType;
  private UUID fkClient;
  private Integer fkClientType;
  private LocalDate serviceDate;
  private LocalTime hourService;
  private String brandVehicle;
  private Integer fkTransport;
  private String origin;
  private String destination;
  private Integer peopleNumber;
  private BigDecimal serviceAmount;
  private String observations;
  private String userName;
  private String userNumber;
  private String userEmail;
  private LocalDateTime creationService;
  private String methodOfPayment;
  private String voucher;
  private Integer fkDriver;
  private String plate;
  private String serviceNumber;
  private String flightNumber;
  private Integer status;
  private Double originLatitude;
  private Double originLongitude;
  private Double destinationLatitude;
  private Double destinationLongitude;
}
