package com.ectransport.platform.domain.application.mapper;

import com.ectransport.platform.domain.application.dto.CreateServiceDto;
import com.ectransport.platform.domain.application.dto.ServiceTypeDto;
import com.ectransport.platform.domain.application.dto.ServicesByUserDto;
import com.ectransport.platform.domain.core.entity.Service;
import com.ectransport.platform.domain.core.entity.ServiceType;
import org.springframework.stereotype.Component;

@Component
public class ServiceApplicationMapper {
  public ServiceTypeDto serviceTypeToServiceTypeDto(ServiceType serviceType) {
    return ServiceTypeDto.builder()
        .id(serviceType.getId())
        .code(serviceType.getCode())
        .name(serviceType.getName())
        .build();
  }

  public CreateServiceDto createServiceToCreateServiceDto(Service service) {
    return CreateServiceDto.builder()
        .serviceId(service.getServiceNumber())
        .build();
  }

  public ServicesByUserDto serviceToServiceByUserDto(Service services) {
    return ServicesByUserDto.builder()
        .idService(services.getIdService())
        .serviceDate(services.getServiceDate())
        .serviceHour(services.getHourService())
        .destination(services.getDestination())
        .origin(services.getOrigin())
        .build();
  }
}
