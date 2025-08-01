package com.ectransport.platform.domain.application.ports.input.service;

import com.ectransport.platform.domain.application.dto.CreateServiceDto;
import com.ectransport.platform.domain.application.dto.RequestCreateServiceDto;
import com.ectransport.platform.domain.application.dto.ServiceTypeDto;
import com.ectransport.platform.domain.application.dto.ServicesByUserDto;

import java.util.List;

public interface ServiceRequestService {
  List<ServiceTypeDto> findAllServiceTypes();

  CreateServiceDto createService(RequestCreateServiceDto requestCreateServiceDto);

  List<ServicesByUserDto> findServiceByUser(Integer id);
}
