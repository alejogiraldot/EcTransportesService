package com.ectransport.platform.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class FileUploadedData {
  private UUID idFile;
  private UUID fkService;
  private String fileName;


}
