package com.ectransport.platform.domain.application.ports.output.service;

import com.ectransport.platform.domain.application.dto.MediaManagerResponseDto;
import com.ectransport.platform.domain.core.entity.FileUpload;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadFileRepository {
  FileUpload saveFileUpload(MediaManagerResponseDto mediaManagerResponseDto);
}
