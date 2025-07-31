package com.ectransport.platform.domain.application.adapter;

import com.ectransport.platform.domain.application.dto.ServiceTypeDto;
import com.ectransport.platform.domain.application.dto.TransportDto;
import com.ectransport.platform.domain.application.mapper.ServiceApplicationMapper;
import com.ectransport.platform.domain.application.ports.input.service.ServiceTypeService;
import com.ectransport.platform.domain.application.ports.output.service.ServiceRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTypeImp implements ServiceTypeService {

  private final ServiceRequestRepository serviceRequestRepository;
  private final ServiceApplicationMapper serviceApplicationMapper;

  public ServiceTypeImp(ServiceRequestRepository serviceRequestRepository, ServiceApplicationMapper serviceApplicationMapper) {
    this.serviceRequestRepository = serviceRequestRepository;
    this.serviceApplicationMapper = serviceApplicationMapper;
  }

  @Override
  public List<ServiceTypeDto> findAllServiceTypes() {
    return serviceRequestRepository.findAllServiceType().stream().map(serviceApplicationMapper::serviceTypeToServiceTypeDto).toList();
  }
}
