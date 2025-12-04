package com.ectransport.platform.api;

import com.ectransport.platform.domain.application.dto.DeleteUploadedDataDto;
import com.ectransport.platform.domain.application.dto.DeleteUploadedDataResponseDto;
import com.ectransport.platform.domain.application.ports.input.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("file-service")
@CrossOrigin("*")
@Slf4j
public class FileServiceController {
  private final UploadService uploadService;

  public FileServiceController(UploadService uploadService) {
    this.uploadService = uploadService;
  }


  @PostMapping("/delete-uploaded-data")
  public ResponseEntity<DeleteUploadedDataResponseDto> deleteUploadedData(@RequestBody DeleteUploadedDataDto deleteUploadedDataDto) {
    DeleteUploadedDataResponseDto response = uploadService.deleteUploadedData(deleteUploadedDataDto);
    return ResponseEntity.ok(response);
  }
}
