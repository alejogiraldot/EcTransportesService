package com.ectransport.platform.domain.application.mapper;

import com.ectransport.platform.domain.application.dto.CreateServiceDto;
import com.ectransport.platform.domain.application.dto.ServiceTypeDto;
import com.ectransport.platform.domain.core.entity.CreateService;
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

  public CreateServiceDto createServiceToCreateServiceDto(CreateService createService) {
    return null;
  }
}
