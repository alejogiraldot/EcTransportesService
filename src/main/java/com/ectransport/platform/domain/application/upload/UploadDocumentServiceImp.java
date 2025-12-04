package com.ectransport.platform.domain.application.upload;

import com.ectransport.platform.domain.application.dto.*;
import com.ectransport.platform.domain.application.ports.output.service.UploadDocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class UploadDocumentServiceImp implements UploadDocumentService {

  private final RestTemplate restTemplate;

  @Value("${upload.document.url}")
  private String uploadUrl;

  @Value("${download.document.url}")
  private String downloadFile;

  @Value("${update.document.url}")
  private String updateDocumentUrl;

  @Value("${delete.document.url}")
  private String deleteUrl;

  public UploadDocumentServiceImp(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public MediaManagerResponseDto uploadDocument(String identification, String fileName, String contentType) throws IOException {
    log.info("Construcción de data para envio");
    String requestUrl = String.format("%s?identification=%s&fileName=%s&contentType=%s",
        uploadUrl, identification, fileName, contentType);
    ResponseEntity<MediaManagerResponseDto> response = restTemplate.postForEntity(
        requestUrl,
        null,
        MediaManagerResponseDto.class
    );
    if (response != null) {
      log.info("Respuesta HTTP Status: {}", response.getStatusCode());
      log.info("Headers: {}", response.getHeaders());
      log.info("Body: {}", response.getBody());
    } else {
      log.warn("La respuesta es null");
    }
    return response.getBody();
  }

  @Override
  public List<FileInfoByServiceDto> downloadDocument(List<FileByServiceDto> fileInfoByServiceDtoList) throws IOException {
    ResponseEntity<FileInfoByServiceDto[]> response = restTemplate.postForEntity(
        downloadFile,
        fileInfoByServiceDtoList,
        FileInfoByServiceDto[].class
    );
    if (response != null) {
      return List.of(response.getBody());
    } else {
      return Collections.emptyList();
    }
  }

  @Override
  public void updateFile(UpdateFileDto route) {
    restTemplate.postForEntity(
        updateDocumentUrl,
        route,
        Void.class
    );
  }

  @Override
  public void deleteFile(DeleteUploadedDataDto deleteUploadedDataDto) {
    restTemplate.postForEntity(
        deleteUrl,
        deleteUploadedDataDto.getRoute(),
        Void.class
    );
  }
}

