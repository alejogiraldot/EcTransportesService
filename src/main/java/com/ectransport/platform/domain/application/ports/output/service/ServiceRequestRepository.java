package com.ectransport.platform.domain.application.ports.output.service;

import com.ectransport.platform.domain.application.dto.RequestCreateServiceDto;
import com.ectransport.platform.domain.core.entity.CreateService;
import com.ectransport.platform.domain.core.entity.ServiceType;

import java.util.List;

public interface ServiceRequestRepository {
  List<ServiceType> findAllServiceType();
  CreateService saveService(RequestCreateServiceDto requestCreateServiceDto);
}
