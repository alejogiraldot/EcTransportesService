package com.ectransport.platform.infrastructure.adapter;

import com.ectransport.platform.domain.application.ports.output.service.EventDataService;
import com.ectransport.platform.domain.application.ports.repository.ServiceEventHistory;
import com.ectransport.platform.infrastructure.entity.EventType;
import com.ectransport.platform.infrastructure.entity.ServiceEventLog;
import com.ectransport.platform.infrastructure.repository.EventTypeRepository;
import com.ectransport.platform.infrastructure.repository.ServiceEventLogRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class EventTypeJpaRepositoryImp implements EventDataService {
  private final EventTypeRepository eventTypeRepository;
  private final ServiceEventLogRepository serviceEventLogRepository;
  public EventTypeJpaRepositoryImp(EventTypeRepository eventTypeRepository, ServiceEventLogRepository serviceEventLogRepository) {
    this.eventTypeRepository = eventTypeRepository;
    this.serviceEventLogRepository = serviceEventLogRepository;
  }


  @Override
  public EventType getEventByCode(String code) {
    return eventTypeRepository.findByCode(code)
        .orElseThrow(() -> new RuntimeException("Event type CREATED not found"));
  }

  @Override
  public void saveEventDataLog(ServiceEventLog serviceEventLog) {
    serviceEventLogRepository.save(serviceEventLog);
  }

  @Override
  public List<ServiceEventHistory> findHistoryById(UUID serviceId) {
    return serviceEventLogRepository.findHistoryByFkService(serviceId);
  }
}
