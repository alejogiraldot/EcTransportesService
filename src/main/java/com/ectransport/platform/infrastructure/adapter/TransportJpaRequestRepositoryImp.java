package com.ectransport.platform.infrastructure.adapter;

import com.ectransport.platform.domain.application.ports.output.service.TransportRequestRepository;
import com.ectransport.platform.domain.core.entity.Transport;
import com.ectransport.platform.infrastructure.mapper.TransportInfrastructureMapper;
import com.ectransport.platform.infrastructure.repository.TransportJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TransportJpaRequestRepositoryImp implements TransportRequestRepository {

  private final TransportJpaRepository transportJpaRepository;
  private final TransportInfrastructureMapper transportInfrastructureMapper;

  public TransportJpaRequestRepositoryImp(TransportJpaRepository transportJpaRepository, TransportInfrastructureMapper transportInfrastructureMapper) {
    this.transportJpaRepository = transportJpaRepository;
    this.transportInfrastructureMapper = transportInfrastructureMapper;
  }

  @Override
  public List<Transport> findById(Integer id) {
    return transportJpaRepository.findByServiceType_Id(id).stream().map(transportInfrastructureMapper::transportEntityToTransport).toList();
  }

  public Optional<Transport> findTransportById(Integer id) {
    return transportJpaRepository.findById(id)
        .map(transportInfrastructureMapper::transportEntityToTransport);
  }
}
