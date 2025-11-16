package com.ectransport.platform.domain.core.entity;

import com.ectransport.platform.domain.application.dto.VehicleBrandDto;

public class Vehicle {
  private Integer id;
  private String name;
  private String plate;
  private String ownerStatus;
  private VehicleBrand vehicleBrand;
  private String status;

  private Vehicle(Builder builder) {
    id = builder.id;
    name = builder.name;
    plate = builder.plate;
    ownerStatus = builder.ownerStatus;
    vehicleBrand = builder.vehicleBrand;
    status = builder.status;
  }

  public String getStatus() {
    return status;
  }

  public static Vehicle.Builder builder() {
    return new Vehicle.Builder();
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getPlate() {
    return plate;
  }

  public String getOwnerStatus() {
    return ownerStatus;
  }

  public VehicleBrand getVehicleBrand() {
    return vehicleBrand;
  }

  public static final class Builder {
    private Integer id;
    private String name;
    private String plate;
    private String ownerStatus;
    private VehicleBrand vehicleBrand;
    private String status;

    private Builder() {
    }

    public static Builder newBuilder() {
      return new Builder();
    }

    public Builder id(Integer val) {
      id = val;
      return this;
    }

    public Builder status(String val) {
      status = val;
      return this;
    }

    public Builder name(String val) {
      name = val;
      return this;
    }

    public Builder plate(String val) {
      plate = val;
      return this;
    }

    public Builder ownerStatus(String val) {
      ownerStatus = val;
      return this;
    }

    public Builder vehicleBrand(VehicleBrand val) {
      vehicleBrand = val;
      return this;
    }

    public Vehicle build() {
      return new Vehicle(this);
    }
  }
}
