package com.ectransport.platform.domain.application.pattern;

import com.ectransport.platform.domain.application.dto.FileUploadResponseDto;
import com.ectransport.platform.domain.application.dto.MediaManagerResponseDto;

import java.util.List;

@FunctionalInterface
public interface ValidateTypeUpload {
  public List<FileUploadResponseDto> validateTypeUpload();
}
