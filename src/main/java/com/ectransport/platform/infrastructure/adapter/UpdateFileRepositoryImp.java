package com.ectransport.platform.infrastructure.adapter;

import com.ectransport.platform.domain.application.dto.ExpenseDataUploadDto;
import com.ectransport.platform.domain.application.dto.UploadDataDto;
import com.ectransport.platform.domain.application.ports.output.service.UpdateDataRepository;
import com.ectransport.platform.infrastructure.repository.UploadRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public class UpdateFileRepositoryImp implements UpdateDataRepository {
  private final UploadRepository uploadRepository;

  public UpdateFileRepositoryImp(UploadRepository uploadRepository) {
    this.uploadRepository = uploadRepository;
  }

  @Override
  @Transactional
  public void updateSettlement(List<UploadDataDto> uploadData) {
    for (UploadDataDto dto : uploadData) {
      if (dto.getIdFile() == null) {
        continue;
      }

      UUID fileUuid = dto.getIdFile();
      uploadRepository.findById(fileUuid)
          .ifPresent(entityToUpdate -> {
            entityToUpdate.setAmount(dto.getAmount());
            entityToUpdate.setPaymentType(dto.getPaymentType());
            entityToUpdate.setBeeper(dto.getBeeper());
            if (dto.getFileName() != null && !dto.getFileName().isEmpty()) {
              String newRoute = dto.getRoute();
              if (newRoute != null) {
                entityToUpdate.setRoute(newRoute);
                entityToUpdate.setFileName(dto.getFileName());
                entityToUpdate.setContentType(dto.getContentType());
              }
            }
            if (dto.getDescription() != null) {
              entityToUpdate.setDescription(dto.getDescription());
            }
          });
    }
  }

  @Override
  @Transactional
  public void updateExpenseSettlement(List<ExpenseDataUploadDto> expenseDataUploadDtoList) {
    for (ExpenseDataUploadDto dto : expenseDataUploadDtoList) {
      if (dto.getIdFile() == null) {
        continue;
      }

      UUID fileUuid = dto.getIdFile();
      uploadRepository.findById(fileUuid)
          .ifPresent(entityToUpdate -> {
            entityToUpdate.setAmount(dto.getAmount());
            entityToUpdate.setPaymentType(dto.getExpense());
            if (dto.getFileName() != null && !dto.getFileName().isEmpty()) {
              String newRoute = dto.getRoute();
              if (newRoute != null) {
                entityToUpdate.setRoute(newRoute);
                entityToUpdate.setFileName(dto.getFileName());
                entityToUpdate.setContentType(dto.getContentType());
              }
            }
            if (dto.getDescription() != null) {
              entityToUpdate.setDescription(dto.getDescription());
            }
          });
    }
  }
}