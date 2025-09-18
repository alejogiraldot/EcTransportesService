package com.ectransport.platform.domain.application.mapper;

import com.ectransport.platform.domain.application.dto.TransportDto;
import com.ectransport.platform.domain.core.entity.Transport;
import org.springframework.stereotype.Component;

@Component
public class TransportApplicationMapper {
  public TransportDto transportToTransportDto(Transport transport) {
    return TransportDto.builder()
        .name(transport.getName())
        .id(transport.getId())
        .code(transport.getCode())
        .build();
  }
}
