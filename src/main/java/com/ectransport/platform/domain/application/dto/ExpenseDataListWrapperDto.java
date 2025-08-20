package com.ectransport.platform.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class ExpenseDataListWrapperDto {
  private List<ExpenseDataUploadDto> expenseDataUpload;

}
