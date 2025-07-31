package com.ectransport.platform.domain.core.entity;

import java.time.LocalDateTime;

public class VehicleBrand {
  private Long id;
  private String name;
  private LocalDateTime creationDate;

  public static VehicleBrand.Builder builder() {
    return new VehicleBrand.Builder();
  }

  private VehicleBrand(Builder builder) {
    id = builder.id;
    name = builder.name;
    creationDate = builder.creationDate;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  public static final class Builder {
    private Long id;
    private String name;
    private LocalDateTime creationDate;

    private Builder() {
    }

    public static Builder newBuilder() {
      return new Builder();
    }

    public Builder id(Long val) {
      id = val;
      return this;
    }

    public Builder name(String val) {
      name = val;
      return this;
    }

    public Builder creationDate(LocalDateTime val) {
      creationDate = val;
      return this;
    }

    public VehicleBrand build() {
      return new VehicleBrand(this);
    }
  }
}
