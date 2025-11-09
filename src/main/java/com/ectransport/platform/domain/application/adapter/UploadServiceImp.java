package com.ectransport.platform.domain.application.adapter;

import com.ectransport.platform.domain.application.dto.*;
import com.ectransport.platform.domain.application.ports.input.service.UploadService;
import com.ectransport.platform.domain.application.ports.output.service.UpdateDataRepository;
import com.ectransport.platform.domain.application.ports.output.service.UploadDocumentService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UploadServiceImp implements UploadService {
  private final UpdateDataRepository updateDataRepository;
  private final UploadDocumentService uploadDocumentService;

  public UploadServiceImp(UpdateDataRepository updateDataRepository, UploadDocumentService uploadDocumentService) {
    this.updateDataRepository = updateDataRepository;
    this.uploadDocumentService = uploadDocumentService;
  }


  @Override
  public List<FileUploadResponseDto> uploadSettlement(String identification, List<UploadDataDto> uploadData, List<ExpenseDataUploadDto> expenseDataUploadDtoList, UUID fkService) throws IOException {
    deleteRoutesPayments(uploadData);
    deleteRouteExpense(expenseDataUploadDtoList);
    List<FileUploadResponseDto> responses = new ArrayList<>();
    responses.addAll(updateNewRoutePayments(uploadData, identification));
    responses.addAll(updateNewRouteExpense(expenseDataUploadDtoList, identification));
    updateDataRepository.updateSettlement(uploadData);
    updateDataRepository.updateExpenseSettlement(expenseDataUploadDtoList);
    return responses;
  }

  private void deleteRoutesPayments(List<UploadDataDto> uploadData) {
    List<String> routesToDelete = new ArrayList<>();

    for (UploadDataDto uploadDataDto : uploadData) {
      if (uploadDataDto.getFileName() != null &&
          !uploadDataDto.getFileName().isEmpty() &&
          uploadDataDto.getRoute() != null) {
        routesToDelete.add(uploadDataDto.getRoute());
      }
    }

    if (!routesToDelete.isEmpty()) {
      uploadDocumentService.updateFile(UpdateFileDto.builder()
          .route(routesToDelete)
          .build());
    }
  }

  private void deleteRouteExpense(List<ExpenseDataUploadDto> expenseDataUploadDtos) {
    List<String> routesToDelete = new ArrayList<>();
    for (ExpenseDataUploadDto expenseDataUploadDto : expenseDataUploadDtos) {
      if (expenseDataUploadDto.getFileName() != null &&
          !expenseDataUploadDto.getFileName().isEmpty() &&
          expenseDataUploadDto.getRoute() != null) {
        routesToDelete.add(expenseDataUploadDto.getRoute());
      }
    }

    if (!routesToDelete.isEmpty()) {
      uploadDocumentService.updateFile(UpdateFileDto.builder()
          .route(routesToDelete)
          .build());
    }
  }

  private List<FileUploadResponseDto> updateNewRoutePayments(List<UploadDataDto> uploadData, String identification) {
    List<FileUploadResponseDto> responses = new ArrayList<>();

    uploadData.forEach(uploadDataDto -> {
      if (uploadDataDto.getFileName() != null &&
          !uploadDataDto.getFileName().isEmpty()) {
        try {
          MediaManagerResponseDto result = uploadDocumentService.uploadDocument(
              identification,
              uploadDataDto.getFileName(),
              uploadDataDto.getContentType()
          );
          uploadDataDto.setRoute(result.getRoute());

          responses.add(FileUploadResponseDto.builder()
              .fileName(uploadDataDto.getFileName())
              .presignedUrl(result.getPresignedUrl())
              .route(result.getRoute())
              .build());

        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    });

    return responses;
  }

  private List<FileUploadResponseDto> updateNewRouteExpense(List<ExpenseDataUploadDto> expenseData, String identification) {
    List<FileUploadResponseDto> responses = new ArrayList<>();

    expenseData.forEach(expenseDataDto -> {
      if (expenseDataDto.getFileName() != null &&
          !expenseDataDto.getFileName().isEmpty()) {
        try {
          MediaManagerResponseDto result = uploadDocumentService.uploadDocument(
              identification,
              expenseDataDto.getFileName(),
              expenseDataDto.getContentType()
          );
          expenseDataDto.setRoute(result.getRoute());

          responses.add(FileUploadResponseDto.builder()
              .fileName(expenseDataDto.getFileName())
              .presignedUrl(result.getPresignedUrl())
              .route(result.getRoute())
              .build());

        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    });

    return responses;
  }
}
