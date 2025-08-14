package com.ectransport.platform.domain.application.ports.output.service;

import com.ectransport.platform.domain.application.dto.MediaManagerResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadDocumentService {
  MediaManagerResponseDto uploadDocument(String identification, MultipartFile file) throws IOException;
}
