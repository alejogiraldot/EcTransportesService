package com.ectransport.platform.domain.application.ports.input.service;

import com.ectransport.platform.domain.application.dto.*;

import java.util.List;

public interface ServiceRequestService {
  List<ServiceTypeDto> findAllServiceTypes();

  CreateServiceDto createService(RequestCreateServiceDto requestCreateServiceDto);

  List<ServiceDto> findServiceByUser(FindServiceByUser findServiceByUser);
  StatusUpdated updateStatusService(UpdateStatus updateStatus);
}
