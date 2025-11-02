package com.ectransport.platform.infrastructure.adapter;

import com.ectransport.platform.domain.application.dto.*;
import com.ectransport.platform.domain.application.ports.output.service.ServiceRequestRepository;
import com.ectransport.platform.domain.core.entity.DailyCounter;
import com.ectransport.platform.domain.core.entity.Service;
import com.ectransport.platform.domain.core.entity.ServiceByUser;
import com.ectransport.platform.domain.core.entity.ServiceType;
import com.ectransport.platform.infrastructure.entity.DailyCounterEntity;
import com.ectransport.platform.infrastructure.entity.ServiceOrderEntity;
import com.ectransport.platform.infrastructure.mapper.ServiceInfrastructureMapper;
import com.ectransport.platform.infrastructure.mapper.UploadFileStructureMapper;
import com.ectransport.platform.infrastructure.repository.DailyCounterRepository;
import com.ectransport.platform.infrastructure.repository.ServiceJpaRepository;
import com.ectransport.platform.infrastructure.repository.ServiceRequestJpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Repository
public class ServiceJpaRequestRepositoryImp implements ServiceRequestRepository {

  private final ServiceRequestJpaRepository serviceRequestJpaRepository;
  private final ServiceInfrastructureMapper serviceInfrastructureMapper;
  private final ServiceJpaRepository serviceJpaRepository;
  private final DailyCounterRepository dailyCounterRepository;
  private final UploadFileStructureMapper uploadFileStructureMapper;

  public ServiceJpaRequestRepositoryImp(ServiceRequestJpaRepository serviceRequestJpaRepository, ServiceInfrastructureMapper serviceInfrastructureMapper, ServiceJpaRepository serviceJpaRepository, DailyCounterRepository dailyCounterRepository, UploadFileStructureMapper uploadFileStructureMapper) {
    this.serviceRequestJpaRepository = serviceRequestJpaRepository;
    this.serviceInfrastructureMapper = serviceInfrastructureMapper;
    this.serviceJpaRepository = serviceJpaRepository;
    this.dailyCounterRepository = dailyCounterRepository;
    this.uploadFileStructureMapper = uploadFileStructureMapper;
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
  public List<ServiceByUser> findServiceByUser(FindServiceByUser findServiceByUser) {
    return serviceJpaRepository.findServiceByUser(
        findServiceByUser.getInitialDate(),
        findServiceByUser.getFinalDate(),
        findServiceByUser.getPlate(),
        findServiceByUser.getUserId(),
        findServiceByUser.getClientId(),
        findServiceByUser.getStatus(),
        findServiceByUser.getDriverId(),
        findServiceByUser.getReference()
    ).stream().map(serviceInfrastructureMapper::serviceReportToService).toList();
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

  @Override
  public void updateStatusService(UpdateStatusDto updateStatusDto) {
    serviceJpaRepository.updateServiceStatus(
        updateStatusDto.getServiceId(),
        updateStatusDto.getId()
    );
  }

  @Override
  public int updateDriverByService(UpdateDriverDto updateDriverDto) {
    return serviceJpaRepository.updateDriverByService(
        updateDriverDto.getServiceId(),
        updateDriverDto.getDriverId(),
        updateDriverDto.getPlate(),
        updateDriverDto.getIdStatus()
    );
  }

  @Override
  public Service findServiceById(UUID id) {
    return serviceJpaRepository.findById(id)
        .map(serviceInfrastructureMapper::serviceEntityToService)
        .orElse(null);
  }

  @Override
  public List<FileByServiceDto> finUploadDataByServiceNumber(String serviceNumber) {
    return serviceJpaRepository.getUploadDataService(serviceNumber).stream().map(uploadFileStructureMapper::uploadDataServiceToFileByServiceDto).toList();
  }


  @Override
  public Integer serviceByDay() {
    ZoneId bogotaZone = ZoneId.of("America/Bogota");
    LocalDate todayBogota = LocalDate.now(bogotaZone);
    return serviceJpaRepository.serviceByDay(todayBogota);
  }

  @Override
  public Integer usersInService() {
    ZoneId bogotaZone = ZoneId.of("America/Bogota");
    LocalDate todayBogota = LocalDate.now(bogotaZone);
    return serviceJpaRepository.usersInService(todayBogota);
  }

  @Override
  public ServiceByUser findServiceByTransactionId(UUID transactionId) {
    return serviceInfrastructureMapper.serviceReportToService(serviceJpaRepository.findServiceByTransactionId(transactionId));
  }

  @Override
  public List<ServiceBySettlementDto> findServiceBySettlement(FindServiceByUser findServiceByUser) {
    return serviceJpaRepository.findServiceBySettlement(
        findServiceByUser.getInitialDate(),
        findServiceByUser.getFinalDate(),
        findServiceByUser.getPlate(),
        findServiceByUser.getUserId(),
        findServiceByUser.getClientId(),
        findServiceByUser.getStatus(),
        findServiceByUser.getDriverId()
    ).stream().map(serviceInfrastructureMapper::serviceSettlementToServiceSettlementDto).toList();
  }

}
