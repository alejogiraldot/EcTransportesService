package com.ectransport.platform.domain.application.ports.output.service;

import com.ectransport.platform.domain.application.dto.MediaManagerResponseDto;

import java.io.IOException;

public interface UploadDocumentService {
  MediaManagerResponseDto uploadDocument(String identification, String fileName, String contetType) throws IOException;
}
