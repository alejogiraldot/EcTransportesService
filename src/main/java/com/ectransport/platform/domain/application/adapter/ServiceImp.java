package com.ectransport.platform.domain.application.adapter;

import com.ectransport.platform.domain.application.dto.CreateServiceDto;
import com.ectransport.platform.domain.application.dto.RequestCreateServiceDto;
import com.ectransport.platform.domain.application.dto.ServiceTypeDto;
import com.ectransport.platform.domain.application.mapper.ServiceApplicationMapper;
import com.ectransport.platform.domain.application.ports.input.service.ServiceRequestService;
import com.ectransport.platform.domain.application.ports.output.service.ServiceRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceImp implements ServiceRequestService {

  private final ServiceRequestRepository serviceRequestRepository;
  private final ServiceApplicationMapper serviceApplicationMapper;

  public ServiceImp(ServiceRequestRepository serviceRequestRepository, ServiceApplicationMapper serviceApplicationMapper) {
    this.serviceRequestRepository = serviceRequestRepository;
    this.serviceApplicationMapper = serviceApplicationMapper;
  }

  @Override
  public List<ServiceTypeDto> findAllServiceTypes() {
    return serviceRequestRepository.findAllServiceType().stream().map(serviceApplicationMapper::serviceTypeToServiceTypeDto).toList();
  }

  @Override
  public CreateServiceDto createService(RequestCreateServiceDto requestCreateServiceDto) {
    return serviceApplicationMapper.createServiceToCreateServiceDto(serviceRequestRepository.saveService(requestCreateServiceDto));
  }
}
