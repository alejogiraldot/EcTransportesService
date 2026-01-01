package com.ectransport.platform.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class HistoryData {
  private String eventCode;
  private String eventName;
  private Object  data;
  private String createdBy;
  private LocalDateTime createdAt;
  private String tradeName;
  private String commit;

}
