package com.ectransport.platform.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class CombinedUploadRequestDto {
  private String identification;
  private UUID fkService;
  private List<UploadDataDto> uploadData;
  private List<ExpenseDataUploadDto> expenseDataUpload;
}

