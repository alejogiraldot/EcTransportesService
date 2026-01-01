package com.ectransport.platform.domain.application.adapter;

import com.ectransport.platform.domain.application.dto.*;
import com.ectransport.platform.domain.application.helpers.ServiceDataEnrichmentService;
import com.ectransport.platform.domain.application.mapper.HistoryApplicationMapper;
import com.ectransport.platform.domain.application.mapper.ServiceApplicationMapper;
import com.ectransport.platform.domain.application.ports.input.service.ServiceRequestService;
import com.ectransport.platform.domain.application.ports.output.service.EventDataService;
import com.ectransport.platform.domain.application.ports.output.service.ServiceRequestRepository;
import com.ectransport.platform.domain.application.ports.output.service.UploadDocumentService;
import com.ectransport.platform.domain.application.strategy.Imp.ValidateTypeExpenseImp;
import com.ectransport.platform.domain.application.strategy.Imp.ValidateTypePaymentImp;
import com.ectransport.platform.domain.core.constans.StatusConstans;
import com.ectransport.platform.domain.core.entity.DailyCounter;
import com.ectransport.platform.infrastructure.entity.EventType;
import com.ectransport.platform.infrastructure.entity.ServiceEventLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ServiceImp implements ServiceRequestService {

  private final ServiceRequestRepository serviceRequestRepository;
  private final ServiceApplicationMapper serviceApplicationMapper;
  private final ValidateTypeExpenseImp validateTypeExpenseImp;
  private final ValidateTypePaymentImp validateTypePaymentImp;
  private final UploadDocumentService uploadDocumentService;
  private final EventDataService eventDataService;
  private final ObjectMapper objectMapper;
  private final HistoryApplicationMapper historyApplicationMapper;
  private final ServiceDataEnrichmentService serviceDataEnrichmentService;

  public ServiceImp(ServiceRequestRepository serviceRequestRepository, ServiceApplicationMapper serviceApplicationMapper, ValidateTypeExpenseImp validateTypeExpenseImp, ValidateTypePaymentImp validateTypePaymentImp, UploadDocumentService uploadDocumentService, EventDataService eventDataService, ObjectMapper objectMapper, HistoryApplicationMapper historyApplicationMapper, ServiceDataEnrichmentService serviceDataEnrichmentService) {
    this.serviceRequestRepository = serviceRequestRepository;
    this.serviceApplicationMapper = serviceApplicationMapper;
    this.validateTypeExpenseImp = validateTypeExpenseImp;
    this.validateTypePaymentImp = validateTypePaymentImp;
    this.uploadDocumentService = uploadDocumentService;
    this.eventDataService = eventDataService;
    this.objectMapper = objectMapper;
    this.historyApplicationMapper = historyApplicationMapper;
    this.serviceDataEnrichmentService = serviceDataEnrichmentService;
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
    serviceDataEnrichmentService.enrichServiceData(requestCreateServiceDto);
    CreateServiceDto response = serviceApplicationMapper.createServiceToCreateServiceDto(serviceRequestRepository.saveService(requestCreateServiceDto));
    saveRequeriment(requestCreateServiceDto);
    createTraceability(requestCreateServiceDto);
    return response;
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
      return serviceApplicationMapper.serviceToServiceUpdatedDto(
          serviceRequestRepository.findServiceById(updateDriverDto.getServiceId())
      );
    } else {
      return null;
    }
  }

  @Override
  public List<FileUploadResponseDto> uploadDocument(
      String identification,
      List<UploadDataDto> uploadData,
      List<ExpenseDataUploadDto> expenseDataUploadDtoList,
      UUID fkService
  ) {
    log.info("Iniciando Solicitud");

    List<FileUploadResponseDto> fileUploadResponseDtoPayment =
        uploadPayments(identification, uploadData, fkService);
    List<FileUploadResponseDto> fileUploadResponseDtoExpense =
        uploadExpense(identification, expenseDataUploadDtoList, fkService);
    List<FileUploadResponseDto> result = new ArrayList<>();
    result.addAll(fileUploadResponseDtoPayment);
    result.addAll(fileUploadResponseDtoExpense);

    return result;
  }

  private void validateDriver(RequestCreateServiceDto requestCreateServiceDto) {
    if ((requestCreateServiceDto.getStatus() == null)) {
      requestCreateServiceDto.setStatus(StatusConstans.CREATED);
    } else if (requestCreateServiceDto.getStatus() == 201) {
      requestCreateServiceDto.setStatus(StatusConstans.ASSIGNED);
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


  private List<FileUploadResponseDto> uploadPayments(String identification,
                                                     List<UploadDataDto> uploadData,
                                                     UUID fkService) {
    log.info("Iniciando Strategy Uploap");
    FileUploadStrategyDto fileUploadStrategyDto = FileUploadStrategyDto.builder()
        .uploadData(uploadData)
        .fkService(fkService)
        .identification(identification)
        .build();
    validateTypePaymentImp.setParameter(fileUploadStrategyDto);
    return validateTypePaymentImp.validateTypeUpload();
  }

  private List<FileUploadResponseDto> uploadExpense(String identification,
                                                    List<ExpenseDataUploadDto> expenseDataUploadDtoList,
                                                    UUID fkService) {
    FileUploadStrategyDto fileUploadStrategyDto = FileUploadStrategyDto.builder()
        .expenseDataUploadDtoList(expenseDataUploadDtoList)
        .fkService(fkService)
        .identification(identification)
        .build();
    validateTypeExpenseImp.setParameter(fileUploadStrategyDto);
    return validateTypeExpenseImp.validateTypeUpload();
  }


  @Override
  public List<FileInfoByServiceDto> downloadDocument(String serviceNumber) throws IOException {
    List<FileByServiceDto> fileByServiceDto = serviceRequestRepository.finUploadDataByServiceNumber(serviceNumber);
    if (fileByServiceDto.isEmpty()) {
      return Collections.emptyList();
    } else {
      return getFilesFromCloud(fileByServiceDto);
    }
  }

  private List<FileInfoByServiceDto> getFilesFromCloud(List<FileByServiceDto> fileByServiceDto) throws IOException {
    return uploadDocumentService.downloadDocument(fileByServiceDto);
  }

  @Override
  public ServiceByDayDto getServiceByDay() {
    return ServiceByDayDto.builder()
        .totalServiceToday(serviceRequestRepository.serviceByDay())
        .usersInService(serviceRequestRepository.usersInService())
        .build();
  }

  @Override
  public ServiceDto findServiceByTransactionId(UUID transactionId) {
    return serviceApplicationMapper.serviceByUserToServiceDto(serviceRequestRepository.findServiceByTransactionId(transactionId));
  }

  @Override
  public List<ServiceBySettlementDto> findServiceBySettlement(FindServiceByUser findServiceByUser) {
    return serviceRequestRepository.findServiceBySettlement(findServiceByUser);
  }


  @Override
  public CreateServiceDto editService(RequestCreateServiceDto requestCreateServiceDto) {
    validateUpdateDriver(requestCreateServiceDto);
    return serviceApplicationMapper.createServiceToCreateServiceDto(serviceRequestRepository.editService(requestCreateServiceDto));
  }

  @Override
  public ServiceNumberDto serviceNumber() {
    LocalDate today = LocalDate.now();
    DailyCounter dailyCounter = serviceRequestRepository.findDailyCounterByDate(today);
    int counter = dailyCounter.getCounter() + 1;
    dailyCounter.setCounter(counter);
    String datePart = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    String counterPart = String.format("%03d", counter);
    String serviceNumber = "Servicio-" + datePart + "-" + counterPart;
    return ServiceNumberDto.builder()
        .serviceNumber(serviceNumber)
        .build();
  }

  @Override
  public List<RequerimentsDto> getRequeriments() {
    return serviceRequestRepository.getRequeriments();
  }

  private void validateUpdateDriver(RequestCreateServiceDto requestCreateServiceDto) {
    if (requestCreateServiceDto.getStatus() == null) {
      requestCreateServiceDto.setStatus(StatusConstans.CREATED);
    } else if (requestCreateServiceDto.getStatus() == 201) {
      requestCreateServiceDto.setStatus(StatusConstans.ASSIGNED);
    }
  }

  public void createTraceability(RequestCreateServiceDto requestCreateServiceDto) {
    try {
      EventType eventType = eventDataService.getEventByCode("CREATED");

      ServiceEventLog eventLog = ServiceEventLog.builder()
          .fkService(requestCreateServiceDto.getIdService())
          .eventType(eventType.getIdEventType())
          .eventPayload(objectMapper.writeValueAsString(requestCreateServiceDto))
          .createdBy(requestCreateServiceDto.getUserInSession())
          .createdAt(LocalDateTime.now())

          .build();
      eventDataService.saveEventDataLog(eventLog);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error serializando payload de trazabilidad", e);
    }
  }

  public void saveRequeriment(RequestCreateServiceDto requestCreateServiceDto) {
    serviceRequestRepository.saveRequeriments(
        requestCreateServiceDto.getIdService(),
        requestCreateServiceDto.getRequeriments()
    );
  }


  @Override
  public List<HistoryData> getHistoryById(UUID serviceId) {
    return eventDataService.findHistoryById(serviceId).stream().map(historyApplicationMapper::serviceEventHistoryToHistoryData).toList();
  }
}
