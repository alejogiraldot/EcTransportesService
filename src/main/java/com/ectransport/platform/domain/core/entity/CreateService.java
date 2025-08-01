package com.ectransport.platform.domain.core.entity;

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
public class CreateService {
  private UUID idService;
  private String serviceType;
  private UUID fkClient;
  private Integer fkClientType;
  private LocalDate serviceDate;
  private LocalTime hourService;
  private String branVehicle;
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

  private CreateService(Builder builder) {
    setIdService(builder.idService);
    setServiceType(builder.serviceType);
    setFkClient(builder.fkClient);
    setFkClientType(builder.fkClientType);
    setServiceDate(builder.serviceDate);
    setHourService(builder.hourService);
    setBranVehicle(builder.branVehicle);
    setFkTransport(builder.fkTransport);
    setOrigin(builder.origin);
    setDestination(builder.destination);
    setPeopleNumber(builder.peopleNumber);
    setServiceAmount(builder.serviceAmount);
    setObservations(builder.observations);
    setUserName(builder.userName);
    setUserNumber(builder.userNumber);
    setUserEmail(builder.userEmail);
    setCreationService(builder.creationService);
    setMethodOfPayment(builder.methodOfPayment);
    setVoucher(builder.voucher);
    setFkDriver(builder.fkDriver);
    setPlate(builder.plate);
    setServiceNumber(builder.serviceNumber);
  }

  public UUID getIdService() {
    return idService;
  }

  public String getServiceType() {
    return serviceType;
  }

  public UUID getFkClient() {
    return fkClient;
  }

  public Integer getFkClientType() {
    return fkClientType;
  }

  public LocalDate getServiceDate() {
    return serviceDate;
  }

  public LocalTime getHourService() {
    return hourService;
  }

  public String getBranVehicle() {
    return branVehicle;
  }

  public Integer getFkTransport() {
    return fkTransport;
  }

  public String getOrigin() {
    return origin;
  }

  public String getDestination() {
    return destination;
  }

  public Integer getPeopleNumber() {
    return peopleNumber;
  }

  public BigDecimal getServiceAmount() {
    return serviceAmount;
  }

  public String getObservations() {
    return observations;
  }

  public String getUserName() {
    return userName;
  }

  public String getUserNumber() {
    return userNumber;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public LocalDateTime getCreationService() {
    return creationService;
  }

  public String getMethodOfPayment() {
    return methodOfPayment;
  }

  public String getVoucher() {
    return voucher;
  }

  public Integer getFkDriver() {
    return fkDriver;
  }

  public String getPlate() {
    return plate;
  }

  public String getServiceNumber() {
    return serviceNumber;
  }

  public static final class Builder {
    private UUID idService;
    private String serviceType;
    private UUID fkClient;
    private Integer fkClientType;
    private LocalDate serviceDate;
    private LocalTime hourService;
    private String branVehicle;
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

    private Builder() {
    }

    public static Builder newBuilder() {
      return new Builder();
    }

    public Builder idService(UUID val) {
      idService = val;
      return this;
    }

    public Builder serviceType(String val) {
      serviceType = val;
      return this;
    }

    public Builder fkClient(UUID val) {
      fkClient = val;
      return this;
    }

    public Builder fkClientType(Integer val) {
      fkClientType = val;
      return this;
    }

    public Builder serviceDate(LocalDate val) {
      serviceDate = val;
      return this;
    }

    public Builder hourService(LocalTime val) {
      hourService = val;
      return this;
    }

    public Builder branVehicle(String val) {
      branVehicle = val;
      return this;
    }

    public Builder fkTransport(Integer val) {
      fkTransport = val;
      return this;
    }

    public Builder origin(String val) {
      origin = val;
      return this;
    }

    public Builder destination(String val) {
      destination = val;
      return this;
    }

    public Builder peopleNumber(Integer val) {
      peopleNumber = val;
      return this;
    }

    public Builder serviceAmount(BigDecimal val) {
      serviceAmount = val;
      return this;
    }

    public Builder observations(String val) {
      observations = val;
      return this;
    }

    public Builder userName(String val) {
      userName = val;
      return this;
    }

    public Builder userNumber(String val) {
      userNumber = val;
      return this;
    }

    public Builder userEmail(String val) {
      userEmail = val;
      return this;
    }

    public Builder creationService(LocalDateTime val) {
      creationService = val;
      return this;
    }

    public Builder methodOfPayment(String val) {
      methodOfPayment = val;
      return this;
    }

    public Builder voucher(String val) {
      voucher = val;
      return this;
    }

    public Builder fkDriver(Integer val) {
      fkDriver = val;
      return this;
    }

    public Builder plate(String val) {
      plate = val;
      return this;
    }

    public Builder serviceNumber(String val) {
      serviceNumber = val;
      return this;
    }

    public CreateService build() {
      return new CreateService(this);
    }
  }
}
