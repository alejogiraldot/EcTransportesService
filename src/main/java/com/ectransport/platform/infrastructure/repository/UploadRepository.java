package com.ectransport.platform.infrastructure.repository;

import com.ectransport.platform.infrastructure.entity.UploadServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UploadRepository extends JpaRepository<UploadServiceEntity, UUID> {
}
