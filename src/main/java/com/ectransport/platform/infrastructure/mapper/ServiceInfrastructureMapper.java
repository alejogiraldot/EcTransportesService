package com.ectransport.platform.infrastructure.mapper;

import com.ectransport.platform.domain.core.entity.ServiceType;
import com.ectransport.platform.infrastructure.entity.ServiceTypeEntity;
import org.springframework.stereotype.Component;

@Component
public class ServiceInfrastructureMapper {

  public ServiceType serviceTypeEntityToServiceType(ServiceTypeEntity entity) {
    return ServiceType.builder()
        .id(entity.getId())
        .code(entity.getCode())
        .name(entity.getName())
        .build();
  }
}
