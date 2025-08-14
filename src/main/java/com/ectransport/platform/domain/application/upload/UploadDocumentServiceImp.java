package com.ectransport.platform.domain.application.upload;

import com.ectransport.platform.domain.application.dto.MediaManagerResponseDto;
import com.ectransport.platform.domain.application.ports.output.service.UploadDocumentService;
import com.ectransport.platform.domain.core.constans.ServiceConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
  public MediaManagerResponseDto uploadDocument(String identification, MultipartFile file) throws IOException {
    File tempFile = File.createTempFile("upload-", file.getOriginalFilename());
    file.transferTo(tempFile);
    MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
    body.add(ServiceConstant.IDENTIFICATION, identification);
    body.add(ServiceConstant.FILE, new FileSystemResource(tempFile));
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
    ResponseEntity<MediaManagerResponseDto> response = restTemplate.postForEntity(uploadUrl, requestEntity, MediaManagerResponseDto.class);
    return response.getBody();
  }

}
