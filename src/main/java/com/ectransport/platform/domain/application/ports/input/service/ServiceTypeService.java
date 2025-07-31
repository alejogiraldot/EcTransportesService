package com.ectransport.platform.domain.application.ports.input.service;

import com.ectransport.platform.domain.application.dto.ServiceTypeDto;

import java.util.List;

public interface ServiceTypeService {
  List<ServiceTypeDto> findAllServiceTypes();

}
