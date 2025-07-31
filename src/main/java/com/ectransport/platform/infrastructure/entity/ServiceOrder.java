package com.ectransport.platform.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "service_orders",schema = "services")
public class ServiceOrder {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_service")
  private Long id;

  @Column(name = "id_transaction", nullable = false)
  private UUID transactionId;

  @Column(name = "fk_user", nullable = false)
  private Integer userId;

  @Column(name = "service_date", nullable = false)
  private LocalDate serviceDate;

  @Column(name = "fk_service_type", nullable = false)
  private Integer serviceTypeId;

  @Column(name = "observations")
  private String observations;

  @Column(name = "reference")
  private String reference;

  @Column(name = "origin", length = 200)
  private String origin;

  @Column(name = "destination", length = 200)
  private String destination;

  @Column(name = "fk_vehicle")
  private Integer vehicleId;

  @Column(name = "number_of_passengers")
  private Integer numberOfPassengers;

  @Column(name = "flight_number", length = 50)
  private String flightNumber;

  @Column(name = "user_info")
  private String userInfo;

  @Column(name = "amount", precision = 10, scale = 2)
  private BigDecimal amount;
}
