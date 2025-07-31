package com.ectransport.platform.domain.application.ports.input.service;

import com.ectransport.platform.domain.application.dto.TransportDto;

import java.util.List;

public interface TransportService {
  List<TransportDto> findByTypeId(Integer typeId);
}
