package com.ectransport.platform.domain.core.entity;

import java.time.LocalDate;

public class DailyCounter {
  private LocalDate date;

  private Integer counter;

  private DailyCounter(Builder builder) {
    date = builder.date;
    counter = builder.counter;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public void setCounter(Integer counter) {
    this.counter = counter;
  }

  public static DailyCounter.Builder builder() {
    return new DailyCounter.Builder();
  }

  public LocalDate getDate() {
    return date;
  }

  public Integer getCounter() {
    return counter;
  }

  public static final class Builder {
    private LocalDate date;
    private Integer counter;

    private Builder() {
    }

    public static Builder newBuilder() {
      return new Builder();
    }

    public Builder date(LocalDate val) {
      date = val;
      return this;
    }

    public Builder counter(Integer val) {
      counter = val;
      return this;
    }

    public DailyCounter build() {
      return new DailyCounter(this);
    }
  }
}
