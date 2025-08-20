package com.ectransport.platform.domain.application.strategy;

import com.ectransport.platform.domain.application.dto.FileUploadResponseDto;
import com.ectransport.platform.domain.application.pattern.ValidateTypeUpload;
import lombok.Setter;

import java.util.List;

public abstract class AbstractValidateTypeUpload<T> implements ValidateTypeUpload {

  @Setter
  protected T parameter;

  @Override
  public List<FileUploadResponseDto> validateTypeUpload() {
    return this.validateTypeUpload(parameter);
  }

  public abstract List<FileUploadResponseDto> validateTypeUpload(T parameter);
}
