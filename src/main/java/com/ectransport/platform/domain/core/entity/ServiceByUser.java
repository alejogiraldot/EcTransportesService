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
  private String transportId;
  private Double originLatitude;
  private Double originLongitude;
  private Double destinationLatitude;
  private Double destinationLongitude;
  private String reference;
  private String product;



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
    transportId = builder.transportId;
    originLatitude = builder.originLatitude;
    originLongitude = builder.originLongitude;
    destinationLatitude = builder.destinationLatitude;
    destinationLongitude = builder.destinationLongitude;
    reference = builder.reference;
    product = builder.product;
  }

  public String getReference() {
    return reference;
  }
  public String getProduct() {
    return product;
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

  public String getPlate() {
    return plate;
  }

  public String getVoucher() {
    return voucher;
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

  public Integer getClientType() {
    return clientType;
  }

  public Double getOriginLatitude() {
    return originLatitude;
  }

  public String getTransportId() {
    return transportId;
  }

  public Double getOriginLongitude() {
    return originLongitude;
  }

  public Double getDestinationLatitude() {
    return destinationLatitude;
  }

  public Double getDestinationLongitude() {
    return destinationLongitude;
  }

  public String getServiceType() {
    return serviceType;
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

  public String getObservations() {
    return observations;
  }

  public String getUserNumber() {
    return userNumber;
  }

  public String getUserName() {
    return userName;
  }


  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private String product;
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
    private String transportId;
    private Double originLatitude;
    private Double originLongitude;
    private Double destinationLatitude;
    private Double destinationLongitude;
    private String reference;

    private Builder() {
    }

    public static Builder newBuilder() {
      return new Builder();
    }

    public Builder serviceType(String val) {
      serviceType = val;
      return this;
    }
    public Builder product(String val) {
      product = val;
      return this;
    }
    public Builder reference(String val) {
      reference = val;
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

    public Builder clientType(Integer val) {
      clientType = val;
      return this;
    }

    public Builder transportId(String val) {
      transportId = val;
      return this;
    }

    public Builder originLatitude(Double val) {
      originLatitude = val;
      return this;
    }

    public Builder originLongitude(Double val) {
      originLongitude = val;
      return this;
    }

    public Builder destinationLatitude(Double val) {
      destinationLatitude = val;
      return this;
    }

    public Builder destinationLongitude(Double val) {
      destinationLongitude = val;
      return this;
    }

    public ServiceByUser build() {
      return new ServiceByUser(this);
    }
  }
}
