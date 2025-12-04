package com.ectransport.platform.domain.application.ports.output.service;

import com.ectransport.platform.domain.application.dto.ExpenseDataUploadDto;
import com.ectransport.platform.domain.application.dto.UploadDataDto;

import java.util.List;
import java.util.UUID;

public interface UpdateDataRepository {
  void updateSettlement(List<UploadDataDto> uploadData);
  void updateExpenseSettlement(List<ExpenseDataUploadDto> expenseDataUploadDtoList);
  boolean deleteFileUploaded(UUID idFile);
}
