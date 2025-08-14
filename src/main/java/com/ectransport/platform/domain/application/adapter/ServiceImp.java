package com.ectransport.platform.domain.application.adapter;

import com.ectransport.platform.domain.application.dto.*;
import com.ectransport.platform.domain.application.mapper.ServiceApplicationMapper;
import com.ectransport.platform.domain.application.mapper.UploadApplicationMapper;
import com.ectransport.platform.domain.application.ports.input.service.ServiceRequestService;
import com.ectransport.platform.domain.application.ports.output.service.ServiceRequestRepository;
import com.ectransport.platform.domain.application.ports.output.service.UploadDocumentService;
import com.ectransport.platform.domain.application.ports.output.service.UploadFileRepository;
import com.ectransport.platform.domain.core.constans.StatusConstans;
import com.ectransport.platform.domain.core.entity.DailyCounter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class ServiceImp implements ServiceRequestService {

  private final ServiceRequestRepository serviceRequestRepository;
  private final ServiceApplicationMapper serviceApplicationMapper;
  private final UploadDocumentService uploadDocumentService;
  private final UploadFileRepository uploadFileRepository;
  private final UploadApplicationMapper uploadApplicationMapper;

  public ServiceImp(ServiceRequestRepository serviceRequestRepository, ServiceApplicationMapper serviceApplicationMapper, UploadDocumentService uploadDocumentService, UploadFileRepository uploadFileRepository, UploadApplicationMapper uploadApplicationMapper) {
    this.serviceRequestRepository = serviceRequestRepository;
    this.serviceApplicationMapper = serviceApplicationMapper;
    this.uploadDocumentService = uploadDocumentService;
    this.uploadFileRepository = uploadFileRepository;
    this.uploadApplicationMapper = uploadApplicationMapper;
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

  @Override
  public StatusUpdatedDto updateStatusService(UpdateStatusDto updateStatusDto) {
    serviceRequestRepository.updateStatusService(updateStatusDto);
    return StatusUpdatedDto.builder()
        .status("El estado fue actualizado con exito")
        .build();
  }

  @Override
  public ServiceUpdatedDto updateDriverByService(UpdateDriverDto updateDriverDto) {
    int statusUpdated = serviceRequestRepository.updateDriverByService(updateDriverDto);
    if (statusUpdated == 1) {
      return serviceApplicationMapper.serviceToServiceUpdatedDto(serviceRequestRepository.findServiceById(updateDriverDto.getServiceId()));
    } else {
      return null;
    }
  }

  @Override
  public FileUploadResponseDto uploadDocument(String identification, MultipartFile file, UUID fkService) throws IOException {
    MediaManagerResponseDto responseUploadDocument = uploadDocumentService.uploadDocument(identification, file);
    responseUploadDocument.setFkService(fkService);
    return uploadApplicationMapper.fileUploadToFileUploadResponseDto(uploadFileRepository.saveFileUpload(responseUploadDocument));
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
