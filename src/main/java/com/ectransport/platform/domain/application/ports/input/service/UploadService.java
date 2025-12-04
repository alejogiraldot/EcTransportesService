package com.ectransport.platform.domain.application.ports.input.service;

import com.ectransport.platform.domain.application.dto.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface UploadService {
  List<FileUploadResponseDto> uploadSettlement(String identification, List<UploadDataDto> uploadData, List<ExpenseDataUploadDto> expenseDataUploadDtoList, UUID fkService) throws IOException;
  DeleteUploadedDataResponseDto deleteUploadedData(DeleteUploadedDataDto deleteUploadedDataDto);
}
