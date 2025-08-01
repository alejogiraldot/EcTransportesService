package com.ectransport.platform.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "service_orders",schema = "services")
public class ServiceOrderEntity {
  @Id
  @Column(name = "id_service")
  private UUID idService;

  @Column(name = "service_type", nullable = false)
  private String serviceType;

  @Column(name = "fk_client", nullable = false)
  private UUID fkClient;

  @Column(name = "fk_client_type", nullable = false)
  private Integer fkClientType;

  @Column(name = "service_date", nullable = false)
  private LocalDate serviceDate;

  @Column(name = "hour_service", nullable = false)
  private LocalTime hourService;

  @Column(name = "bran_vehicle", nullable = false)
  private String branVehicle;

  @Column(name = "fk_transport", nullable = false)
  private Integer fkTransport;

  @Column(name = "origin", nullable = false)
  private String origin;

  @Column(name = "destination", nullable = false)
  private String destination;

  @Column(name = "people_number", nullable = false)
  private Integer peopleNumber;

  @Column(name = "service_ammount", nullable = false)
  private BigDecimal serviceAmmount;

  @Column(name = "observations")
  private String observations;

  @Column(name = "user_name", nullable = false)
  private String userName;

  @Column(name = "user_number", nullable = false)
  private String userNumber;

  @Column(name = "user_email", nullable = false)
  private String userEmail;

  @Column(name = "creation_service")
  private LocalDateTime creationService;

  @Column(name = "method_of_payment", nullable = false)
  private String methodOfPayment;

  @Column(name = "voucher")
  private String voucher;

  @Column(name = "fk_driver")
  private Integer fkDriver;

  @Column(name = "plate", nullable = false)
  private String plate;

  @Column(name = "service_number", nullable = false)
  private String serviceNumber;
}
