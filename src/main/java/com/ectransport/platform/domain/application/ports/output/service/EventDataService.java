package com.ectransport.platform.domain.application.ports.output.service;

import com.ectransport.platform.domain.application.ports.repository.ServiceEventHistory;
import com.ectransport.platform.infrastructure.entity.EventType;
import com.ectransport.platform.infrastructure.entity.ServiceEventLog;

import java.util.List;
import java.util.UUID;

public interface EventDataService {
  EventType getEventByCode(String code);
  void saveEventDataLog(ServiceEventLog serviceEventLog);
  List<ServiceEventHistory> findHistoryById(UUID serviceId);
}
