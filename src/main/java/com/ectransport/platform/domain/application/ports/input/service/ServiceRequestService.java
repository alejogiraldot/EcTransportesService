package com.ectransport.platform.domain.application.ports.input.service;

import com.ectransport.platform.domain.application.dto.*;
import com.ectransport.platform.domain.core.entity.DailyCounter;

import java.time.LocalDate;
import java.util.List;

public interface ServiceRequestService {
  List<ServiceTypeDto> findAllServiceTypes();

  CreateServiceDto createService(RequestCreateServiceDto requestCreateServiceDto);

  List<ServicesByUserDto> findServiceByUser(FindServiceByUser findServiceByUser);

}
