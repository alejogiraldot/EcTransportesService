package com.ectransport.platform.domain.application.strategy.Imp;

import com.ectransport.platform.domain.application.dto.FileUploadResponseDto;
import com.ectransport.platform.domain.application.dto.FileUploadStrategyDto;
import com.ectransport.platform.domain.application.dto.MediaManagerResponseDto;
import com.ectransport.platform.domain.application.dto.UploadDataDto;
import com.ectransport.platform.domain.application.mapper.UploadApplicationMapper;
import com.ectransport.platform.domain.application.ports.output.service.UploadDocumentService;
import com.ectransport.platform.domain.application.ports.output.service.UploadFileRepository;
import com.ectransport.platform.domain.application.strategy.AbstractValidateTypeUpload;
import com.ectransport.platform.domain.core.constans.UploadConstant;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ValidateTypePaymentImp extends AbstractValidateTypeUpload<FileUploadStrategyDto> {

  private final UploadDocumentService uploadDocumentService;
  private final UploadFileRepository uploadFileRepository;
  private final UploadApplicationMapper uploadApplicationMapper;

  public ValidateTypePaymentImp(UploadDocumentService uploadDocumentService, UploadFileRepository uploadFileRepository, UploadApplicationMapper uploadApplicationMapper) {
    this.uploadDocumentService = uploadDocumentService;
    this.uploadFileRepository = uploadFileRepository;
    this.uploadApplicationMapper = uploadApplicationMapper;
  }

  @Override
  public List<FileUploadResponseDto> validateTypeUpload(FileUploadStrategyDto parameter) {
    return Optional.ofNullable(parameter.getUploadData())
        .filter(list -> !list.isEmpty())
        .map(list -> list.stream()
            .map(uploadDataDto -> handleUploadData(
                parameter.getIdentification(),
                uploadDataDto,
                parameter.getFkService()))
            .toList())
        .orElse(List.of());
  }


  private FileUploadResponseDto handleUploadData(
      String identification,
      UploadDataDto uploadDataDto,
      UUID fkService
  ) {
    try {
      MediaManagerResponseDto response;

      if (uploadDataDto.getFileName() != null) {
        response = processWithFile(identification, uploadDataDto, fkService);
      } else {
        response = processWithoutFile(uploadDataDto, fkService);
      }

      return mapAndSaveResponse(response);
    } catch (IOException e) {
      throw new RuntimeException("Error al procesar el archivo: " + uploadDataDto, e);
    }
  }

  private MediaManagerResponseDto processWithFile(
      String identification,
      UploadDataDto uploadDataDto,
      UUID fkService
  ) throws IOException {
    MediaManagerResponseDto responseUploadDocument =
        uploadDocumentService.uploadDocument(identification, uploadDataDto.getFileName(), uploadDataDto.getContentType());

    setValueUploadData(uploadDataDto, responseUploadDocument, fkService);
    return responseUploadDocument;
  }

  private void setValueUploadData(UploadDataDto uploadDataDto, MediaManagerResponseDto mediaManagerResponseDto, UUID fkService) {
    mediaManagerResponseDto.setFkService(fkService);
    mediaManagerResponseDto.setBeeper(uploadDataDto.getBeeper());
    mediaManagerResponseDto.setAmount(uploadDataDto.getAmount());
    mediaManagerResponseDto.setPaymentType(uploadDataDto.getPaymentType());
    mediaManagerResponseDto.setFkTypeUpload(UploadConstant.PAYMENT_KEY);
  }

  private MediaManagerResponseDto processWithoutFile(
      UploadDataDto uploadDataDto,
      UUID fkService
  ) {
    return setDataWithoutUpload(uploadDataDto, fkService);
  }


  private FileUploadResponseDto mapAndSaveResponse(MediaManagerResponseDto response) {
    FileUploadResponseDto fileUploadResponseDto = uploadApplicationMapper.fileUploadToFileUploadResponseDto(
        uploadFileRepository.saveFileUpload(response));
    fileUploadResponseDto.setPresignedUrl(response.getPresignedUrl());
    return fileUploadResponseDto;

  }

  private MediaManagerResponseDto setDataWithoutUpload(UploadDataDto uploadDataDto, UUID fkService) {
    return MediaManagerResponseDto.builder()
        .amount(uploadDataDto.getAmount())
        .fkService(fkService)
        .beeper(uploadDataDto.getBeeper())
        .route(null)
        .contentType(null)
        .description(uploadDataDto.getDescription())
        .paymentType(uploadDataDto.getPaymentType())
        .fileName(null)
        .fileSize(null)
        .uploadDate(LocalDateTime.now())
        .fkTypeUpload(UploadConstant.PAYMENT_KEY)
        .build();
  }
}
