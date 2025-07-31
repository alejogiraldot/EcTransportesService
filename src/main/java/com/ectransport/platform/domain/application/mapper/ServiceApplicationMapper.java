package com.ectransport.platform.domain.application.mapper;

import com.ectransport.platform.domain.application.dto.ServiceTypeDto;
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
}
