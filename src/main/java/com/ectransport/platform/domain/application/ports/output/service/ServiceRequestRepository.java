package com.ectransport.platform.domain.application.ports.output.service;

import com.ectransport.platform.domain.application.dto.FindServiceByUser;
import com.ectransport.platform.domain.application.dto.RequestCreateServiceDto;
import com.ectransport.platform.domain.application.dto.ServicesByUserDto;
import com.ectransport.platform.domain.application.dto.UpdateStatus;
import com.ectransport.platform.domain.core.entity.DailyCounter;
import com.ectransport.platform.domain.core.entity.Service;
import com.ectransport.platform.domain.core.entity.ServiceByUser;
import com.ectransport.platform.domain.core.entity.ServiceType;

import java.time.LocalDate;
import java.util.List;

public interface ServiceRequestRepository {
  List<ServiceType> findAllServiceType();
  Service saveService(RequestCreateServiceDto requestCreateServiceDto);
  List<ServiceByUser> findServiceByUser(FindServiceByUser id);
  DailyCounter findDailyCounterByDate(LocalDate localDate);
  void saveCounter(DailyCounter dailyCounter);
  void updateStatusService(UpdateStatus updateStatus);
}
