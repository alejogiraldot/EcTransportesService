package com.ectransport.platform.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDataUploadDto {
  private String expense;
  private String fileName;
  private String contentType;
  private Integer amount;
  private String description;
  private String identification;
  private String fileUrl;
  private UUID idFile;
}
