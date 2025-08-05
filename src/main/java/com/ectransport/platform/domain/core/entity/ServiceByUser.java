package com.ectransport.platform.domain.core.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

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

  public static ServiceByUser.Builder builder() {
    return new ServiceByUser.Builder();
  }

  public ServiceByUser(String serviceType, LocalDate serviceDate, LocalTime hourService, String brandVehicle, String origin, String destination, Integer peopleNumber, BigDecimal serviceAmmount, String observations, String userNumber, String userName, String userEmail, String flightNumber, String methodOfPayment, String voucher, String plate, String serviceNumber, String driverName, String driverLastName, String clientName) {
    this.serviceType = serviceType;
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
  }

  private ServiceByUser(Builder builder) {
    setServiceType(builder.serviceType);
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
    setMethodOfPayment(builder.methodOfPayment);
    setVoucher(builder.voucher);
    setPlate(builder.plate);
    setServiceNumber(builder.serviceNumber);
    setDriverName(builder.driverName);
    setDriverLastName(builder.driverLastName);
    setClientName(builder.clientName);
  }

  public String getClientName() {
    return clientName;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public String getFlightNumber() {
    return flightNumber;
  }

  public String getVoucher() {
    return voucher;
  }

  public String getMethodOfPayment() {
    return methodOfPayment;
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

  public void setServiceType(String serviceType) {
    this.serviceType = serviceType;
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
  }

  public void setDriverLastName(String driverLastName) {
    this.driverLastName = driverLastName;
  }

  public void setDriverName(String driverName) {
    this.driverName = driverName;
  }

  public void setServiceNumber(String serviceNumber) {
    this.serviceNumber = serviceNumber;
  }

  public void setPlate(String plate) {
    this.plate = plate;
  }

  public void setVoucher(String voucher) {
    this.voucher = voucher;
  }

  public void setMethodOfPayment(String methodOfPayment) {
    this.methodOfPayment = methodOfPayment;
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

    private Builder() {
    }

    public static Builder newBuilder() {
      return new Builder();
    }

    public Builder serviceType(String val) {
      serviceType = val;
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

    public ServiceByUser build() {
      return new ServiceByUser(this);
    }
  }
}
