package com.ectransport.platform.domain.core.entity;

public class Expense {
  private Integer id;
  private String name;
  private String code;


  public static Expense.Builder builder() {
    return new Expense.Builder();
  }

  public Expense(Integer id, String name, String code) {
    this.id = id;
    this.name = name;
    this.code = code;
  }

  private Expense(Builder builder) {
    id = builder.id;
    name = builder.name;
    code = builder.code;
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

    public Expense build() {
      return new Expense(this);
    }
  }
}
