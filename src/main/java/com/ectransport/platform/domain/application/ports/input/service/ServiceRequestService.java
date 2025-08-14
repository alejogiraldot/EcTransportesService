package com.ectransport.platform.domain.application.ports.input.service;

import com.ectransport.platform.domain.application.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ServiceRequestService {
  List<ServiceTypeDto> findAllServiceTypes();

  CreateServiceDto createService(RequestCreateServiceDto requestCreateServiceDto);

  List<ServiceDto> findServiceByUser(FindServiceByUser findServiceByUser);

  StatusUpdatedDto updateStatusService(UpdateStatusDto updateStatusDto);

  ServiceUpdatedDto updateDriverByService(UpdateDriverDto updateDriverDto);

  FileUploadResponseDto uploadDocument(String identification, MultipartFile file, UUID fkService) throws IOException;

}
