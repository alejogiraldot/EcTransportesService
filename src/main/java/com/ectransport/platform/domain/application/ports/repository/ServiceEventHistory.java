package com.ectransport.platform.domain.application.ports.repository;

import java.time.LocalDateTime;

public interface ServiceEventHistory {
  Long getId();
  Integer getEventType();
  String getEventCode();
  String getEventPayload();
  String getCreatedBy();
  LocalDateTime getCreatedAt();
  String getTradeName();
  String getCommit();
}
