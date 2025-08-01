package com.ectransport.platform.infrastructure.adapter;

import com.ectransport.platform.domain.application.dto.RequestCreateServiceDto;
import com.ectransport.platform.domain.application.ports.output.service.ServiceRequestRepository;
import com.ectransport.platform.domain.core.entity.Service;
import com.ectransport.platform.domain.core.entity.ServiceType;
import com.ectransport.platform.infrastructure.entity.ServiceOrderEntity;
import com.ectransport.platform.infrastructure.mapper.ServiceInfrastructureMapper;
import com.ectransport.platform.infrastructure.repository.ServiceJpaRepository;
import com.ectransport.platform.infrastructure.repository.ServiceRequestJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServiceJpaRequestRepositoryImp implements ServiceRequestRepository {

  private final ServiceRequestJpaRepository serviceRequestJpaRepository;
  private final ServiceInfrastructureMapper serviceInfrastructureMapper;
  private final ServiceJpaRepository serviceJpaRepository;

  public ServiceJpaRequestRepositoryImp(ServiceRequestJpaRepository serviceRequestJpaRepository, ServiceInfrastructureMapper serviceInfrastructureMapper, ServiceJpaRepository serviceJpaRepository) {
    this.serviceRequestJpaRepository = serviceRequestJpaRepository;
    this.serviceInfrastructureMapper = serviceInfrastructureMapper;
    this.serviceJpaRepository = serviceJpaRepository;
  }

  @Override
  public List<ServiceType> findAllServiceType() {
    return serviceRequestJpaRepository
        .findAll()
        .stream()
        .map(serviceInfrastructureMapper::serviceTypeEntityToServiceType)
        .toList();
  }

  @Override
  public Service saveService(RequestCreateServiceDto requestCreateServiceDto) {
    ServiceOrderEntity serviceOrderEntity = serviceInfrastructureMapper.requestCreateServiceDtoToServiceOrderEntity(requestCreateServiceDto);
    return serviceInfrastructureMapper.serviceEntityToService(serviceJpaRepository.save(serviceOrderEntity));
  }

  @Override
  public List<Service> findServiceByUser(Integer id) {
    return serviceJpaRepository.findByFkDriver(id).stream().map(serviceInfrastructureMapper::serviceEntityToService).toList();
  }
}
