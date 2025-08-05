package com.ectransport.platform.infrastructure.adapter;

import com.ectransport.platform.domain.application.dto.FindServiceByUser;
import com.ectransport.platform.domain.application.dto.RequestCreateServiceDto;
import com.ectransport.platform.domain.application.ports.output.service.ServiceRequestRepository;
import com.ectransport.platform.domain.core.entity.DailyCounter;
import com.ectransport.platform.domain.core.entity.Service;
import com.ectransport.platform.domain.core.entity.ServiceType;
import com.ectransport.platform.infrastructure.entity.DailyCounterEntity;
import com.ectransport.platform.infrastructure.entity.ServiceOrderEntity;
import com.ectransport.platform.infrastructure.mapper.ServiceInfrastructureMapper;
import com.ectransport.platform.infrastructure.repository.DailyCounterRepository;
import com.ectransport.platform.infrastructure.repository.ServiceJpaRepository;
import com.ectransport.platform.infrastructure.repository.ServiceRequestJpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ServiceJpaRequestRepositoryImp implements ServiceRequestRepository {

  private final ServiceRequestJpaRepository serviceRequestJpaRepository;
  private final ServiceInfrastructureMapper serviceInfrastructureMapper;
  private final ServiceJpaRepository serviceJpaRepository;
  private final DailyCounterRepository dailyCounterRepository;

  public ServiceJpaRequestRepositoryImp(ServiceRequestJpaRepository serviceRequestJpaRepository, ServiceInfrastructureMapper serviceInfrastructureMapper, ServiceJpaRepository serviceJpaRepository, DailyCounterRepository dailyCounterRepository) {
    this.serviceRequestJpaRepository = serviceRequestJpaRepository;
    this.serviceInfrastructureMapper = serviceInfrastructureMapper;
    this.serviceJpaRepository = serviceJpaRepository;
    this.dailyCounterRepository = dailyCounterRepository;
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
  public List<Service> findServiceByUser(FindServiceByUser findServiceByUser) {
    return serviceJpaRepository.findServiceByUser(
        findServiceByUser.getClientId(),
        findServiceByUser.getFinalDate(),
        findServiceByUser.getInitialDate(),
        findServiceByUser.getPlate()
    ).stream().map(serviceInfrastructureMapper::serviceEntityToService).toList();
  }

  @Override
  public DailyCounter findDailyCounterByDate(LocalDate localDate) {
    return dailyCounterRepository.findById(localDate)
        .map(serviceInfrastructureMapper::dailyCounterEntityToDailyCounter)
        .orElseGet(() -> DailyCounter.builder()
            .date(localDate)
            .counter(0)
            .build());
  }

  @Override
  public void saveCounter(DailyCounter dailyCounter) {
    DailyCounterEntity dailyCounterEntity = serviceInfrastructureMapper.dailyCounterToDailyCounterDto(dailyCounter);
    dailyCounterRepository.save(dailyCounterEntity);
  }
}
