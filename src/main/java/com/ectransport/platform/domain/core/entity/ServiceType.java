package com.ectransport.platform.domain.core.entity;

public class ServiceType {
  private Integer id;
  private String name;
  private String code;

  private ServiceType(Builder builder) {
    id = builder.id;
    name = builder.name;
    code = builder.code;
  }
  public static Builder builder() {
    return new Builder();
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

  public static final class Builder {
    private Integer id;
    private String name;
    private String code;

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

    public ServiceType build() {
      return new ServiceType(this);
    }
  }
}
