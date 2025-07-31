package com.ectransport.platform.infrastructure.mapper;

import com.ectransport.platform.domain.core.entity.ServiceType;
import com.ectransport.platform.domain.core.entity.Transport;
import com.ectransport.platform.infrastructure.entity.ServiceTypeEntity;
import com.ectransport.platform.infrastructure.entity.TransportEntity;
import org.springframework.stereotype.Component;

@Component
public class TransportInfrastructureMapper {

  public Transport transportEntityToTransport(TransportEntity transportEntity){
    return  Transport.builder()
        .id(transportEntity.getId())
        .name(transportEntity.getName())
        .code(transportEntity.getCode())
        .serviceType(serviceTypeEntityToServiceType(transportEntity.getServiceType()))
        .build();
  }

  public ServiceType serviceTypeEntityToServiceType(ServiceTypeEntity serviceTypeEntity){
    return ServiceType.builder()
        .code(serviceTypeEntity.getCode())
        .name(serviceTypeEntity.getName())
        .build();
  }
}
