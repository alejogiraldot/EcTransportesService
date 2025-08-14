package com.ectransport.platform.infrastructure.adapter;

import com.ectransport.platform.domain.application.dto.MediaManagerResponseDto;
import com.ectransport.platform.domain.application.ports.output.service.UploadFileRepository;
import com.ectransport.platform.domain.core.entity.FileUpload;
import com.ectransport.platform.infrastructure.entity.UploadServiceEntity;
import com.ectransport.platform.infrastructure.mapper.UploadFileStructureMapper;
import com.ectransport.platform.infrastructure.repository.UploadRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UploadJpaRepositoryImp implements UploadFileRepository {

  private final UploadRepository uploadFileRepository;
  private final UploadFileStructureMapper uploadFileStructureMapper;

  public UploadJpaRepositoryImp(UploadRepository uploadFileRepository, UploadFileStructureMapper uploadFileStructureMapper) {
    this.uploadFileRepository = uploadFileRepository;
    this.uploadFileStructureMapper = uploadFileStructureMapper;
  }


  @Override
  public FileUpload saveFileUpload(MediaManagerResponseDto mediaManagerResponseDto) {
    UploadServiceEntity uploadServiceEntity = uploadFileRepository.save(uploadFileStructureMapper.fileUploadResponseDtoToUploadServiceEntity(mediaManagerResponseDto));
    return uploadFileStructureMapper.uploadServiceEntityToFileUploaded(uploadServiceEntity);
  }
}
