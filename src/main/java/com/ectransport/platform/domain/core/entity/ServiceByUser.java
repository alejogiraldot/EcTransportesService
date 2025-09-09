package com.ectransport.platform.domain.core.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class ServiceByUser {
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
  private UUID idService;
  private Integer statusIdentifier;
  private Integer driverId;
  private String userIdentification;
  private Integer clientType;

  public static ServiceByUser.Builder builder() {
    return new ServiceByUser.Builder();
  }

  public ServiceByUser(String serviceType, LocalDate serviceDate, LocalTime hourService, String brandVehicle, String origin, String destination, Integer peopleNumber, BigDecimal serviceAmmount, String observations, String userNumber, String userName, String userEmail, String flightNumber, String methodOfPayment, String voucher, String plate, String serviceNumber, String driverName, String driverLastName, String clientName, Integer statusId, UUID idService, Integer statusIdentifier, Integer driverId, String userIdentification, Integer clientType) {
    this.serviceType = serviceType;
    this.clientType =clientType;
    this.serviceDate = serviceDate;
    this.hourService = hourService;
    this.brandVehicle = brandVehicle;
    this.origin = origin;
    this.destination = destination;
    this.peopleNumber = peopleNumber;
    this.serviceAmmount = serviceAmmount;
    this.observations = observations;
    this.userNumber = userNumber;
    this.userName = userName;
    this.userEmail = userEmail;
    this.flightNumber = flightNumber;
    this.methodOfPayment = methodOfPayment;
    this.voucher = voucher;
    this.plate = plate;
    this.serviceNumber = serviceNumber;
    this.driverName = driverName;
    this.driverLastName = driverLastName;
    this.clientName = clientName;
    this.statusId = statusId;
    this.idService = idService;
    this.statusIdentifier = statusIdentifier;
    this.driverId = driverId;
    this.userIdentification = userIdentification;
  }

  private ServiceByUser(Builder builder) {
    serviceType = builder.serviceType;
    serviceDate = builder.serviceDate;
    hourService = builder.hourService;
    brandVehicle = builder.brandVehicle;
    origin = builder.origin;
    destination = builder.destination;
    peopleNumber = builder.peopleNumber;
    serviceAmmount = builder.serviceAmmount;
    observations = builder.observations;
    userNumber = builder.userNumber;
    userName = builder.userName;
    userEmail = builder.userEmail;
    flightNumber = builder.flightNumber;
    methodOfPayment = builder.methodOfPayment;
    voucher = builder.voucher;
    plate = builder.plate;
    serviceNumber = builder.serviceNumber;
    driverName = builder.driverName;
    driverLastName = builder.driverLastName;
    clientName = builder.clientName;
    statusId = builder.statusId;
    idService = builder.idService;
    statusIdentifier = builder.statusIdentifier;
    driverId = builder.driverId;
    userIdentification = builder.userIdentification;
    clientType = builder.clientType;
  }

  public String getServiceType() {
    return serviceType;
  }

  public Integer getClientType() {
    return clientType;
  }
  public LocalDate getServiceDate() {
    return serviceDate;
  }

  public LocalTime getHourService() {
    return hourService;
  }

  public String getBrandVehicle() {
    return brandVehicle;
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

  public BigDecimal getServiceAmmount() {
    return serviceAmmount;
  }

  public String getUserNumber() {
    return userNumber;
  }

  public String getObservations() {
    return observations;
  }

  public String getUserName() {
    return userName;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public String getFlightNumber() {
    return flightNumber;
  }

  public String getMethodOfPayment() {
    return methodOfPayment;
  }

  public String getVoucher() {
    return voucher;
  }

  public String getPlate() {
    return plate;
  }

  public String getServiceNumber() {
    return serviceNumber;
  }

  public String getDriverName() {
    return driverName;
  }

  public String getDriverLastName() {
    return driverLastName;
  }

  public String getClientName() {
    return clientName;
  }

  public Integer getStatusId() {
    return statusId;
  }

  public UUID getIdService() {
    return idService;
  }

  public Integer getStatusIdentifier() {
    return statusIdentifier;
  }

  public Integer getDriverId() {
    return driverId;
  }

  public String getUserIdentification() {
    return userIdentification;
  }


  public static final class Builder {
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
    private UUID idService;
    private Integer statusIdentifier;
    private Integer driverId;
    private String userIdentification;
    private Integer clientType;

    private Builder() {
    }

    public static Builder newBuilder() {
      return new Builder();
    }

    public Builder serviceType(String val) {
      serviceType = val;
      return this;
    }

    public Builder clientType(Integer val) {
      clientType = val;
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

    public Builder brandVehicle(String val) {
      brandVehicle = val;
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

    public Builder serviceAmmount(BigDecimal val) {
      serviceAmmount = val;
      return this;
    }

    public Builder observations(String val) {
      observations = val;
      return this;
    }

    public Builder userNumber(String val) {
      userNumber = val;
      return this;
    }

    public Builder userName(String val) {
      userName = val;
      return this;
    }

    public Builder userEmail(String val) {
      userEmail = val;
      return this;
    }

    public Builder flightNumber(String val) {
      flightNumber = val;
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

    public Builder plate(String val) {
      plate = val;
      return this;
    }

    public Builder serviceNumber(String val) {
      serviceNumber = val;
      return this;
    }

    public Builder driverName(String val) {
      driverName = val;
      return this;
    }

    public Builder driverLastName(String val) {
      driverLastName = val;
      return this;
    }

    public Builder clientName(String val) {
      clientName = val;
      return this;
    }

    public Builder statusId(Integer val) {
      statusId = val;
      return this;
    }

    public Builder idService(UUID val) {
      idService = val;
      return this;
    }

    public Builder statusIdentifier(Integer val) {
      statusIdentifier = val;
      return this;
    }

    public Builder driverId(Integer val) {
      driverId = val;
      return this;
    }

    public Builder userIdentification(String val) {
      userIdentification = val;
      return this;
    }

    public ServiceByUser build() {
      return new ServiceByUser(this);
    }
  }
}
