package com.ectransport.platform.domain.application.ports.input.service;

import com.ectransport.platform.domain.application.dto.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ServiceRequestService {
  List<ServiceTypeDto> findAllServiceTypes();

  CreateServiceDto createService(RequestCreateServiceDto requestCreateServiceDto);

  List<ServiceDto> findServiceByUser(FindServiceByUser findServiceByUser);

  StatusUpdatedDto updateStatusService(UpdateStatusDto updateStatusDto);

  ServiceUpdatedDto updateDriverByService(UpdateDriverDto updateDriverDto);

  List<FileUploadResponseDto> uploadDocument(String identification, List<UploadDataDto> uploadData, List<ExpenseDataUploadDto> expenseDataUploadDtoList, UUID fkService) throws IOException;

  List<FileInfoByServiceDto> downloadDocument(String serviceNumber) throws IOException;

  ServiceByDayDto getServiceByDay();
}
