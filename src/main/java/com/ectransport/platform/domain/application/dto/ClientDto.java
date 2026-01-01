package com.ectransport.platform.domain.application.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Builder
@Data
@AllArgsConstructor
public class ClientDto {
  private UUID clientId;
  private String legalName;
  private String tradeName;
  private String taxId;
  private LocalDateTime creationDate;
  private ClientTypeDto clientTypeDto;
  private String email;
  private String phoneNumber;
  private String contractNumber;
  private String identificationType;
  private String status;

}
