package com.ectransport.platform.domain.application.upload;

import com.ectransport.platform.domain.application.dto.MediaManagerResponseDto;
import com.ectransport.platform.domain.application.ports.output.service.UploadDocumentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class UploadDocumentServiceImp implements UploadDocumentService {

  private final RestTemplate restTemplate;
  @Value("${upload.document.url}")
  private String uploadUrl;

  public UploadDocumentServiceImp(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public MediaManagerResponseDto uploadDocument(String identification, String fileName, String contentType) throws IOException {
    String requestUrl = String.format("%s?identification=%s&fileName=%s&contentType=%s",
        uploadUrl, identification, fileName, contentType);
    ResponseEntity<MediaManagerResponseDto> response = restTemplate.postForEntity(
        requestUrl,
        null,
        MediaManagerResponseDto.class
    );
    return response.getBody();
  }
}

