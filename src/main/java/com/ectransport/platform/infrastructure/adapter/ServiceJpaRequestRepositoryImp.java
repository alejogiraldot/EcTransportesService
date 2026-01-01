package com.ectransport.platform.infrastructure.adapter;

import com.ectransport.platform.domain.application.dto.*;
import com.ectransport.platform.domain.application.helpers.ChangeDetectionService;
import com.ectransport.platform.domain.application.helpers.ChangeEnrichmentService;
import com.ectransport.platform.domain.application.ports.output.service.EventDataService;
import com.ectransport.platform.domain.application.ports.output.service.ServiceRequestRepository;
import com.ectransport.platform.domain.core.entity.DailyCounter;
import com.ectransport.platform.domain.core.entity.Service;
import com.ectransport.platform.domain.core.entity.ServiceByUser;
import com.ectransport.platform.domain.core.entity.ServiceType;
import com.ectransport.platform.infrastructure.entity.*;
import com.ectransport.platform.infrastructure.mapper.ServiceInfrastructureMapper;
import com.ectransport.platform.infrastructure.mapper.UploadFileStructureMapper;
import com.ectransport.platform.infrastructure.repository.*;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ServiceJpaRequestRepositoryImp implements ServiceRequestRepository {

  private final ServiceRequestJpaRepository serviceRequestJpaRepository;
  private final ServiceInfrastructureMapper serviceInfrastructureMapper;
  private final ServiceJpaRepository serviceJpaRepository;
  private final DailyCounterRepository dailyCounterRepository;
  private final UploadFileStructureMapper uploadFileStructureMapper;
  private final RequerimentJpaRepository requerimentJpaRepository;
  private final ServiceRequirementJpaRepository serviceRequirementJpaRepository;
  private final ChangeDetectionService changeDetectionService;
  private final EventDataService eventDataService;
  private final ChangeEnrichmentService changeEnrichmentService;
  private final EntityManager entityManager; // ✅ NUEVO

  public ServiceJpaRequestRepositoryImp(ServiceRequestJpaRepository serviceRequestJpaRepository, ServiceInfrastructureMapper serviceInfrastructureMapper, ServiceJpaRepository serviceJpaRepository, DailyCounterRepository dailyCounterRepository, UploadFileStructureMapper uploadFileStructureMapper, RequerimentJpaRepository requerimentJpaRepository, ServiceRequirementJpaRepository serviceRequirementJpaRepository, ChangeDetectionService changeDetectionService, EventDataService eventDataService, ChangeEnrichmentService changeEnrichmentService, EntityManager entityManager) {
    this.serviceRequestJpaRepository = serviceRequestJpaRepository;
    this.serviceInfrastructureMapper = serviceInfrastructureMapper;
    this.serviceJpaRepository = serviceJpaRepository;
    this.dailyCounterRepository = dailyCounterRepository;
    this.uploadFileStructureMapper = uploadFileStructureMapper;
    this.requerimentJpaRepository = requerimentJpaRepository;
    this.serviceRequirementJpaRepository = serviceRequirementJpaRepository;
    this.changeDetectionService = changeDetectionService;
    this.eventDataService = eventDataService;
    this.changeEnrichmentService = changeEnrichmentService;
    this.entityManager = entityManager;
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
        findServiceByUser.getReference(),
        findServiceByUser.getServiceNumber()
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
    ServiceOrderEntity serviceOrderEntity = serviceJpaRepository
        .findById(updateStatusDto.getServiceId())
        .orElseThrow(() -> new RuntimeException(
            "Servicio no encontrado con ID: " + updateStatusDto.getServiceId()
        ));

    Integer oldStatusId = serviceOrderEntity.getFkServiceStatus();
    Integer newStatusId = updateStatusDto.getId();
    if (!Objects.equals(oldStatusId, newStatusId)) {
      Map<String, Map<String, Object>> changes = new HashMap<>();
      Map<String, Object> statusChange = new HashMap<>();
      statusChange.put("initialValue", oldStatusId);
      statusChange.put("lastValue", newStatusId);
      statusChange.put("fieldName", "Estado del servicio");
      changes.put("fkServiceStatus", statusChange);
      changeEnrichmentService.enrichChanges(changes);
      serviceJpaRepository.updateServiceStatus(
          updateStatusDto.getServiceId(),
          newStatusId
      );
      saveStatusChangeTraceability(changes, serviceOrderEntity,updateStatusDto);
    }
  }

  private void saveStatusChangeTraceability(
      Map<String, Map<String, Object>> changes,
      ServiceOrderEntity serviceOrderEntity,
      UpdateStatusDto updateStatusDto) {

    if (changes.isEmpty()) {
      return;
    }

    try {
      EventType eventType = eventDataService.getEventByCode("UPDATED");
      String changesJson = changeDetectionService.changesToJson(changes);

      ServiceEventLog eventLog = ServiceEventLog.builder()
          .fkService(serviceOrderEntity.getIdService())
          .eventType(eventType.getIdEventType())
          .eventPayload(changesJson)
          .createdBy(updateStatusDto.getUserInSession())
          .createdAt(LocalDateTime.now())
          .commit(updateStatusDto.getId() == 8 ? updateStatusDto.getCommit() : "") // ✅ Ternario
          .build();

      eventDataService.saveEventDataLog(eventLog);

    } catch (Exception e) {
      throw new RuntimeException("Error guardando trazabilidad de cambio de estado", e);
    }
  }
  @Override
  @Transactional
  public int updateDriverByService(UpdateDriverDto updateDriverDto) {
    ServiceOrderEntity serviceOrderEntity = serviceJpaRepository
        .findById(updateDriverDto.getServiceId())
        .orElseThrow(() -> new RuntimeException(
            "Servicio no encontrado con ID: " + updateDriverDto.getServiceId()
        ));

    Integer oldDriverId = serviceOrderEntity.getFkDriver();
    Integer newDriverId = updateDriverDto.getDriverId();

    Integer oldStatusId = serviceOrderEntity.getFkServiceStatus();
    Integer newStatusId = updateDriverDto.getIdStatus();

    String oldPlate = serviceOrderEntity.getPlate();
    String newPlate = updateDriverDto.getPlate();

    Map<String, Map<String, Object>> changes = new HashMap<>();

    if (!Objects.equals(oldDriverId, newDriverId)) {
      Map<String, Object> driverChange = new HashMap<>();
      driverChange.put("initialValue", oldDriverId);
      driverChange.put("lastValue", newDriverId);
      driverChange.put("fieldName", "Conductor");
      changes.put("fkDriver", driverChange);
    }

    if (!Objects.equals(oldStatusId, newStatusId)) {
      Map<String, Object> statusChange = new HashMap<>();
      statusChange.put("initialValue", oldStatusId);
      statusChange.put("lastValue", newStatusId);
      statusChange.put("fieldName", "Estado del servicio");
      changes.put("fkServiceStatus", statusChange);
    }

    if (!Objects.equals(oldPlate, newPlate)) {
      Map<String, Object> plateChange = new HashMap<>();
      plateChange.put("initialValue", oldPlate != null ? oldPlate : "");
      plateChange.put("lastValue", newPlate != null ? newPlate : "");
      plateChange.put("fieldName", "Placa");
      changes.put("plate", plateChange);
    }

    if (!changes.isEmpty()) {
      changeEnrichmentService.enrichChanges(changes);
    }

    int result = serviceJpaRepository.updateDriverByService(
        updateDriverDto.getServiceId(),
        newDriverId,
        newPlate,
        newStatusId
    );

    if (result > 0) {
      serviceJpaRepository.flush();
      entityManager.clear();

      if (!changes.isEmpty()) {
        saveDriverAndStatusChangeTraceability(changes, serviceOrderEntity, updateDriverDto);
      }
    }

    return result;
  }

  /**
   * Guardar trazabilidad de cambios de conductor, estado Y/O placa
   */
  private void saveDriverAndStatusChangeTraceability(
      Map<String, Map<String, Object>> changes,
      ServiceOrderEntity serviceOrderEntity,
      UpdateDriverDto updateDriverDto) {

    if (changes.isEmpty()) {
      return;
    }

    try {
      EventType eventType = eventDataService.getEventByCode("UPDATED");
      String changesJson = changeDetectionService.changesToJson(changes);

      ServiceEventLog eventLog = ServiceEventLog.builder()
          .fkService(serviceOrderEntity.getIdService())
          .eventType(eventType.getIdEventType())
          .eventPayload(changesJson)
          .createdBy(updateDriverDto.getUserInSession())
          .createdAt(LocalDateTime.now())
          .commit("")
          .build();

      eventDataService.saveEventDataLog(eventLog);

    } catch (Exception e) {
      throw new RuntimeException("Error guardando trazabilidad de actualización", e);
    }
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
        findServiceByUser.getDriverId(),
        findServiceByUser.getServiceNumber()
    ).stream().map(serviceInfrastructureMapper::serviceSettlementToServiceSettlementDto).toList();
  }

  @Override
  public Service editService(RequestCreateServiceDto requestCreateServiceDto) {
    ServiceOrderEntity serviceOrderEntity = serviceInfrastructureMapper
        .requestCreateServiceDtoToServiceOrderEntity(requestCreateServiceDto);

    ServiceOrderEntity oldServiceOrderEntity = serviceJpaRepository
        .findById(serviceOrderEntity.getIdService())
        .orElseThrow(() -> new RuntimeException(
            "Servicio no encontrado con ID: " + serviceOrderEntity.getIdService()
        ));

    // ✅ PASO 1: Leer requerimientos viejos
    List<Integer> oldRequirements = serviceRequirementJpaRepository
        .findByIdService(serviceOrderEntity.getIdService())
        .stream()
        .map(ServiceRequirement::getIdRequirement)
        .collect(Collectors.toList());

    // ✅ PASO 2: Detectar cambios
    Map<String, Map<String, Object>> changes = changeDetectionService.detectServiceChanges(
        oldServiceOrderEntity,
        serviceOrderEntity,
        oldRequirements,
        requestCreateServiceDto.getRequeriments()
    );

    // ✅ CRÍTICO: Preservar campos que no deben cambiar
    serviceOrderEntity.setCreationService(oldServiceOrderEntity.getCreationService());
    serviceOrderEntity.setServiceNumber(oldServiceOrderEntity.getServiceNumber());

    // ✅ CRÍTICO: Manejar el status correctamente
    if (requestCreateServiceDto.getStatus() == 1 || requestCreateServiceDto.getStatus() == 2) {
      serviceOrderEntity.setFkServiceStatus(requestCreateServiceDto.getStatus());
    } else {
      serviceOrderEntity.setFkServiceStatus(oldServiceOrderEntity.getFkServiceStatus());
    }

    // ✅ PASO 3: Guardar servicio
    ServiceOrderEntity savedService = serviceJpaRepository.save(serviceOrderEntity);

    // ✅ PASO 4: Actualizar requerimientos
    handleServiceRequirements(savedService.getIdService(), requestCreateServiceDto.getRequeriments());

    // ✅ PASO 5: Guardar trazabilidad
    validateSaveTraceability(changes, serviceOrderEntity, requestCreateServiceDto);

    return serviceInfrastructureMapper.serviceEntityToService(savedService);
  }

  private void handleServiceRequirements(UUID idService, List<Integer> requirements) {
    serviceRequirementJpaRepository.deleteByIdService(idService);
    serviceRequirementJpaRepository.flush();
    if (requirements != null && !requirements.isEmpty()) {
      List<ServiceRequirement> newRequirements = requirements.stream()
          .map(requirementId -> ServiceRequirement.builder()
              .idService(idService)
              .idRequirement(requirementId)
              .build())
          .collect(Collectors.toList());

      serviceRequirementJpaRepository.saveAll(newRequirements);
    }
  }

  @Override
  public List<RequerimentsDto> getRequeriments() {
    return requerimentJpaRepository.findAll()
        .stream()
        .map(serviceInfrastructureMapper::requerimentsToRequerimentsDto)
        .toList();
  }

  @Override
  public void saveRequeriments(UUID getIdService, List<Integer> requeriments) {
    if (getIdService == null || requeriments.isEmpty()) {
      return;
    }

    List<ServiceRequirement> entities = requeriments.stream()
        .map(reqId -> ServiceRequirement.builder()
            .idService(getIdService)
            .idRequirement(reqId)
            .build()
        )
        .toList();
    serviceRequirementJpaRepository.saveAll(entities);
  }

  void validateSaveTraceability(Map<String, Map<String, Object>> changes, ServiceOrderEntity serviceOrderEntity, RequestCreateServiceDto requestCreateServiceDto) {
    if (!changes.isEmpty()) {
      saveUpdateTraceability(
          serviceOrderEntity.getIdService(),
          changes,
          requestCreateServiceDto.getUserInSession()
      );
    }
  }

  private void saveUpdateTraceability(
      UUID serviceId,
      Map<String, Map<String, Object>> changes,
      String userInSession) {
    try {
      EventType eventType = eventDataService.getEventByCode("UPDATED");
      String changesJson = changeDetectionService.changesToJson(changes);

      ServiceEventLog eventLog = ServiceEventLog.builder()
          .fkService(serviceId)
          .eventType(eventType.getIdEventType())
          .eventPayload(changesJson)
          .createdBy(userInSession)
          .createdAt(LocalDateTime.now())
          .build();
      eventDataService.saveEventDataLog(eventLog);
    } catch (Exception e) {
      throw new RuntimeException("Error guardando trazabilidad de actualización", e);
    }
  }
}
