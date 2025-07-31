package com.ectransport.platform.infrastructure.adapter;

import com.ectransport.platform.domain.application.ports.output.service.ServiceRequestRepository;
import com.ectransport.platform.domain.core.entity.ServiceType;
import com.ectransport.platform.infrastructure.mapper.ServiceInfrastructureMapper;
import com.ectransport.platform.infrastructure.repository.ServiceTypeJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServiceTypeJpaRequestRepositoryImp implements ServiceRequestRepository {

  private final ServiceTypeJpaRepository serviceTypeJpaRepository;
  private final ServiceInfrastructureMapper serviceInfrastructureMapper;

  public ServiceTypeJpaRequestRepositoryImp(ServiceTypeJpaRepository serviceTypeJpaRepository, ServiceInfrastructureMapper serviceInfrastructureMapper) {
    this.serviceTypeJpaRepository = serviceTypeJpaRepository;
    this.serviceInfrastructureMapper = serviceInfrastructureMapper;
  }

  @Override
  public List<ServiceType> findAllServiceType() {
    return serviceTypeJpaRepository
        .findAll()
        .stream()
        .map(serviceInfrastructureMapper::serviceTypeEntityToServiceType)
        .toList();
  }
}
