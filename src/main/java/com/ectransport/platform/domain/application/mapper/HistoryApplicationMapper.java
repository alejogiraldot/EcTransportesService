package com.ectransport.platform.domain.application.mapper;

import com.ectransport.platform.domain.application.dto.HistoryData;
import com.ectransport.platform.domain.application.ports.repository.ServiceEventHistory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class HistoryApplicationMapper {

  private final ObjectMapper objectMapper;

  public HistoryApplicationMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public HistoryData serviceEventHistoryToHistoryData(ServiceEventHistory history) {
    try {
      return HistoryData.builder()
          .eventCode(history.getEventCode())
          .data(objectMapper.readValue(history.getEventPayload(), Object.class))
          .createdBy(history.getCreatedBy())
          .createdAt(history.getCreatedAt())
          .tradeName(history.getTradeName())
          .commit(history.getCommit())
          .build();
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error parseando event_payload del historial", e);
    }
  }
}
