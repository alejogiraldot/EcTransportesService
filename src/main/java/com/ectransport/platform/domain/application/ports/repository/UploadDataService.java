package com.ectransport.platform.domain.application.ports.repository;

public interface UploadDataService {
  String getFileName();
  String getRoute();
  String getBeeper();
  String getPaymentType();
  String getDescription();
  String getAmount();
  Integer getFkTypeUpload();
  String getLegalName();
  Integer getClientType();
  String getPlate();
  Integer getServiceAmount();
  String getName();
  String getLastname();

}
