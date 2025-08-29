package com.ectransport.platform.domain.application.ports.output.service;

import com.ectransport.platform.domain.application.dto.FileByServiceDto;
import com.ectransport.platform.domain.application.dto.FileInfoByServiceDto;
import com.ectransport.platform.domain.application.dto.MediaManagerResponseDto;

import java.io.IOException;
import java.util.List;

public interface UploadDocumentService {
  MediaManagerResponseDto uploadDocument(String identification, String fileName, String contetType) throws IOException;
  List<FileInfoByServiceDto> downloadDocument(List<FileByServiceDto> fileInfoByServiceDtoList) throws IOException;
}
