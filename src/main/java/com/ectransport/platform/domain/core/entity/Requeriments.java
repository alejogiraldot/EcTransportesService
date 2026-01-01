package com.ectransport.platform.domain.core.entity;

import java.util.UUID;

public class Requeriments {
  private UUID idService;
  private Integer idRequeriment;

  private Requeriments(Builder builder) {
    idService = builder.idService;
    idRequeriment = builder.idRequeriment;
  }

  public UUID getIdService() {
    return idService;
  }

  public Integer getIdRequeriment() {
    return idRequeriment;
  }


  public static final class Builder {
    private UUID idService;
    private Integer idRequeriment;

    private Builder() {
    }

    public static Builder newBuilder() {
      return new Builder();
    }

    public Builder idService(UUID val) {
      idService = val;
      return this;
    }

    public Builder idRequeriment(Integer val) {
      idRequeriment = val;
      return this;
    }

    public Requeriments build() {
      return new Requeriments(this);
    }
  }
}
