package com.ectransport.platform.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "daily_counters",schema = "services")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailyCounterEntity {
  @Id
  private LocalDate date;

  private Integer counter;

}
