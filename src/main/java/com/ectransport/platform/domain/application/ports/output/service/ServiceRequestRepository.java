package com.ectransport.platform.domain.application.ports.output.service;

import com.ectransport.platform.domain.application.dto.RequestCreateServiceDto;
import com.ectransport.platform.domain.application.dto.ServicesByUserDto;
import com.ectransport.platform.domain.core.entity.Service;
import com.ectransport.platform.domain.core.entity.ServiceType;

import java.util.List;

public interface ServiceRequestRepository {
  List<ServiceType> findAllServiceType();
  Service saveService(RequestCreateServiceDto requestCreateServiceDto);
  List<Service> findServiceByUser(Integer id);

}
