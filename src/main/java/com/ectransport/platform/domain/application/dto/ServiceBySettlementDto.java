package com.ectransport.platform.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class ServiceBySettlementDto {
  private String serviceNumber;
  private String methodOfPayment;
  private String clientName;
  private String clientType;
  private String origin;
  private String destination;
  private String driverName;
  private String driverLastName;
  private String plate;
  private String brand;
  private Integer serviceAmount;
  private Integer beeper;
  private Integer tollAmount;
  private Integer parking;
  private Integer wash;
  private Integer gasoline;
  private Integer extra;
  private Integer tip;
  private Integer flypass;
  private Integer washShip;
  private Integer gasolineShip;
  private String serviceType;
  private UUID idService;
  private LocalDate serviceDate;
  private LocalTime hourService;
  private UUID idFile;
  private Integer peopleNumber;
  private String observations;
  private String userNumber;
  private String userName;
  private String userEmail;
  private String flightNumber;
  private String voucher;
  private Integer statusId;
  private Integer statusIdentifier;
  private Integer driverId;
  private String userIdentification;
  private String transportId;
  private String reference;
  private Integer totalService;
  private String product;
}
