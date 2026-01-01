package com.ectransport.platform.domain.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
  private Integer id;
  private String email;
  private Integer fkUserType;
  private String name;
  private String lastName;
  private String phoneNumber;
  private String identification;
  private String identificationType;
  private String status;
}
