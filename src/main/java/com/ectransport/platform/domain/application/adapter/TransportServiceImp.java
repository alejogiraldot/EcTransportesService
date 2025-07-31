package com.ectransport.platform.domain.application.adapter;

import com.ectransport.platform.domain.application.dto.TransportDto;
import com.ectransport.platform.domain.application.mapper.TransportApplicationMapper;
import com.ectransport.platform.domain.application.ports.input.service.TransportService;
import com.ectransport.platform.domain.application.ports.output.service.TransportRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportServiceImp implements TransportService {
  private final TransportRequestRepository transportRequestRepository;
  private final TransportApplicationMapper transportApplicationMapper;

  public TransportServiceImp(TransportRequestRepository transportRequestRepository, TransportApplicationMapper transportApplicationMapper) {
    this.transportRequestRepository = transportRequestRepository;
    this.transportApplicationMapper = transportApplicationMapper;
  }

  @Override
  public List<TransportDto> findByTypeId(Integer typeId) {
    return transportRequestRepository.findById(typeId).stream().map(transportApplicationMapper::transportToTransportDto).toList();
  }
}
