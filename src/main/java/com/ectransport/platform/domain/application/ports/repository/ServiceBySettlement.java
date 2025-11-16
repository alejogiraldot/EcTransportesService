package com.ectransport.platform.domain.application.ports.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public interface ServiceBySettlement {
  String getServiceNumber();

  String getMethodOfPayment();

  String getClientName();

  String getClientType();

  String getOrigin();

  String getDestination();

  String getDriverName();

  String getDriverLastName();

  String getPlate();

  String getServiceType();

  String getBrand();

  Integer getServiceAmmount();

  Integer getBeeper();

  Integer getTollAmount();

  Integer getParking();

  Integer getWash();

  Integer getGasoline();

  Integer getExtra();

  Integer getTip();

  Integer getFlypass();

  Integer getWashShip();

  Integer getGasolineShip();

  UUID getIdService();

  LocalDate getServiceDate();

  LocalTime getHourService();

  UUID getIdFile();

  Integer getPeopleNumber();

  String getObservations();

  String getUserNumber();

  String getUserName();

  String getUserEmail();

  String getFlightNumber();

  String getVoucher();

  Integer getStatusId();

  Integer getStatusIdentifier();

  Integer getDriverId();

  String getUserIdentification();

  String getTransportId();

  String getReference();

  Integer getTotalService();

  String getProduct();
}