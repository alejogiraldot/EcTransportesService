package com.ectransport.platform.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "upload_files", schema = "services")
public class UploadServiceEntity {

  @Id
  @GeneratedValue
  @Column(name = "id_file", nullable = false, updatable = false)
  private UUID idFile;

  @Column(name = "fk_service", nullable = false)
  private UUID fkService;

  @Column(name = "file_name", nullable = false, length = 255)
  private String fileName;

  @Column(name = "route", nullable = false, length = 500)
  private String route;

  @Column(name = "content_type", length = 100)
  private String contentType;

  @Column(name = "file_size")
  private Long fileSize;

  @Column(name = "upload_date", insertable = false, updatable = false)
  private LocalDateTime uploadDate;
}
