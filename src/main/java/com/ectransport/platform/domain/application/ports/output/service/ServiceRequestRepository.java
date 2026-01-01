package com.ectransport.platform.domain.application.ports.output.service;

import com.ectransport.platform.domain.application.dto.*;
import com.ectransport.platform.domain.core.entity.DailyCounter;
import com.ectransport.platform.domain.core.entity.Service;
import com.ectransport.platform.domain.core.entity.ServiceByUser;
import com.ectransport.platform.domain.core.entity.ServiceType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface ServiceRequestRepository {
  List<ServiceType> findAllServiceType();
  Service saveService(RequestCreateServiceDto requestCreateServiceDto);
  List<ServiceByUser> findServiceByUser(FindServiceByUser id);
  DailyCounter findDailyCounterByDate(LocalDate localDate);
  void saveCounter(DailyCounter dailyCounter);
  void updateStatusService(UpdateStatusDto updateStatusDto);
  int updateDriverByService (UpdateDriverDto updateDriverDto);
  Service findServiceById(UUID id);
  List<FileByServiceDto> finUploadDataByServiceNumber(String serviceNumber);
  Integer serviceByDay();
  Integer usersInService();
  ServiceByUser findServiceByTransactionId(UUID transactionId);
  List<ServiceBySettlementDto> findServiceBySettlement(FindServiceByUser findServiceByUser);
  Service editService(RequestCreateServiceDto requestCreateServiceDto);
  List<RequerimentsDto> getRequeriments();
  void saveRequeriments(UUID getIdService,List<Integer> requeriments);
}
