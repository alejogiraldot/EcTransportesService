package com.ectransport.platform.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class DeleteUploadedDataDto {
  private UUID idFile;
  private String route;
  private String presignedUrl;
}
