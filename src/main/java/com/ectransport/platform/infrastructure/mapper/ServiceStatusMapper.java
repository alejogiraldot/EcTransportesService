package com.ectransport.platform.infrastructure.mapper;

import com.ectransport.platform.domain.application.dto.StatusDto;
import com.ectransport.platform.infrastructure.entity.StatusEntity;
import org.springframework.stereotype.Component;

@Component
public class ServiceStatusMapper {

  public StatusDto statusEntityToStatusDto(StatusEntity statusEntity){
    return StatusDto.builder()
        .id(statusEntity.getId())
        .name(statusEntity.getName())
        .statusId(statusEntity.getStatusId())
        .build();
  }
}
