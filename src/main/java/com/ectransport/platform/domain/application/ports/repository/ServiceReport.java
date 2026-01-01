package com.ectransport.platform.domain.application.ports.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public interface ServiceReport {
  String getServiceType();

  LocalDate getServiceDate();

  LocalTime getHourService();

  String getBrandVehicle();

  String getOrigin();

  String getDestination();

  Integer getPeopleNumber();

  BigDecimal getServiceAmmount();

  String getObservations();

  String getUserNumber();

  String getUserName();

  String getUserEmail();

  String getFlightNumber();

  String getMethodOfPayment();

  String getVoucher();

  String getPlate();

  String getServiceNumber();

  String getDriverName();

  String getDriverLastName();

  String getClientName();

  Integer getStatusId();

  UUID getIdService();

  Integer getStatusIdentifier();

  Integer getDriverId();

  String getUserIdentification();

  Integer getClientType();

  String getTransportId();

  Double getOriginLatitude();

  Double getOriginLongitude();

  Double getDestinationLatitude();

  Double getDestinationLongitude();

  String getReference();

  String getProduct();

  String getRequirementIds();
}
