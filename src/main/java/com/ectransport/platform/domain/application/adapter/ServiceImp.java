package com.ectransport.platform.domain.application.adapter;

import com.ectransport.platform.domain.application.dto.*;
import com.ectransport.platform.domain.application.mapper.ServiceApplicationMapper;
import com.ectransport.platform.domain.application.ports.input.service.ServiceRequestService;
import com.ectransport.platform.domain.application.ports.output.service.ServiceRequestRepository;
import com.ectransport.platform.domain.core.constans.StatusConstans;
import com.ectransport.platform.domain.core.entity.DailyCounter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class ServiceImp implements ServiceRequestService {

  private final ServiceRequestRepository serviceRequestRepository;
  private final ServiceApplicationMapper serviceApplicationMapper;

  public ServiceImp(ServiceRequestRepository serviceRequestRepository, ServiceApplicationMapper serviceApplicationMapper) {
    this.serviceRequestRepository = serviceRequestRepository;
    this.serviceApplicationMapper = serviceApplicationMapper;
  }

  @Override
  public List<ServiceTypeDto> findAllServiceTypes() {
    return serviceRequestRepository.findAllServiceType().stream().map(serviceApplicationMapper::serviceTypeToServiceTypeDto).toList();
  }

  @Override
  public CreateServiceDto createService(RequestCreateServiceDto requestCreateServiceDto) {
    generateIdService(requestCreateServiceDto);
    generateCreationDate(requestCreateServiceDto);
    validateDriver(requestCreateServiceDto);
    generateServiceIdentification(requestCreateServiceDto);
    return serviceApplicationMapper.createServiceToCreateServiceDto(serviceRequestRepository.saveService(requestCreateServiceDto));
  }

  @Override
  public List<ServiceDto> findServiceByUser(FindServiceByUser findServiceByUser) {
    return serviceRequestRepository.findServiceByUser(findServiceByUser).stream().map(serviceApplicationMapper::serviceByUserToServiceDto).toList();
  }


  private void validateDriver(RequestCreateServiceDto requestCreateServiceDto) {
    if ((requestCreateServiceDto.getStatus() == null)) {
      requestCreateServiceDto.setStatus(StatusConstans.CREATED);
    } else {
      requestCreateServiceDto.setStatus(StatusConstans.ASSIGNED);
    }
  }

  private void generateIdService(RequestCreateServiceDto requestCreateServiceDto) {
    UUID idService = UUID.randomUUID();
    requestCreateServiceDto.setIdService(idService);
  }

  private void generateCreationDate(RequestCreateServiceDto requestCreateServiceDto) {
    LocalDateTime localDateTime = LocalDateTime.now();
    requestCreateServiceDto.setCreationService(localDateTime);
  }

  private void generateServiceIdentification(RequestCreateServiceDto requestCreateServiceDto) {
    LocalDate today = LocalDate.now();
    DailyCounter dailyCounter = serviceRequestRepository.findDailyCounterByDate(today);
    int counter = dailyCounter.getCounter() + 1;
    dailyCounter.setCounter(counter);
    serviceRequestRepository.saveCounter(dailyCounter);
    String datePart = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    String counterPart = String.format("%03d", counter);
    String serviceNumber = "Servicio-" + datePart + "-" + counterPart;
    requestCreateServiceDto.setServiceNumber(serviceNumber);
  }

}
