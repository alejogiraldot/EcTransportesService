package com.ectransport.platform.domain.core.entity;

import com.ectransport.platform.infrastructure.entity.ServiceOrder;

public class Transport {
  private Integer id;
  private String name;
  private String code;
  private ServiceType serviceType;

  private Transport(Builder builder) {
    id = builder.id;
    name = builder.name;
    code = builder.code;
    serviceType = builder.serviceType;
  }
  public static Transport.Builder builder() {
    return new Transport.Builder();
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }

  public ServiceType getServiceType() {
    return serviceType;
  }

  public static final class Builder {
    private Integer id;
    private String name;
    private String code;
    private ServiceType serviceType;

    private Builder() {
    }

    public static Builder newBuilder() {
      return new Builder();
    }

    public Builder id(Integer val) {
      id = val;
      return this;
    }

    public Builder name(String val) {
      name = val;
      return this;
    }

    public Builder code(String val) {
      code = val;
      return this;
    }

    public Builder serviceType(ServiceType val) {
      serviceType = val;
      return this;
    }

    public Transport build() {
      return new Transport(this);
    }
  }
}
