package com.ectransport.platform.domain.application.ports.repository;

public interface UploadDataService {
  String getFileName();
  String getRoute();
  String getBeeper();
  String getPaymentType();
  String getDescription();
  String getAmount();
  Integer getFkTypeUpload();
}
