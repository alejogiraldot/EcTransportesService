package com.ectransport.platform.domain.core.entity;

import java.time.LocalDateTime;

public class FileUpload {
  private String fileName;
  private String route;
  private Long fileSize;
  private LocalDateTime uploadDate;


  public static FileUpload.Builder builder() {
    return new FileUpload.Builder();
  }

  private FileUpload(Builder builder) {
    fileName = builder.fileName;
    route = builder.route;
    fileSize = builder.fileSize;
    uploadDate = builder.uploadDate;
  }

  public String getFileName() {
    return fileName;
  }

  public String getRoute() {
    return route;
  }

  public Long getFileSize() {
    return fileSize;
  }

  public LocalDateTime getUploadDate() {
    return uploadDate;
  }

  public FileUpload(String fileName, String route, Long fileSize, LocalDateTime uploadDate) {
    this.fileName = fileName;
    this.route = route;
    this.fileSize = fileSize;
    this.uploadDate = uploadDate;
  }


  public static final class Builder {
    private String fileName;
    private String route;
    private Long fileSize;
    private LocalDateTime uploadDate;

    private Builder() {
    }

    public static Builder newBuilder() {
      return new Builder();
    }

    public Builder fileName(String val) {
      fileName = val;
      return this;
    }

    public Builder route(String val) {
      route = val;
      return this;
    }

    public Builder fileSize(Long val) {
      fileSize = val;
      return this;
    }

    public Builder uploadDate(LocalDateTime val) {
      uploadDate = val;
      return this;
    }

    public FileUpload build() {
      return new FileUpload(this);
    }
  }
}
