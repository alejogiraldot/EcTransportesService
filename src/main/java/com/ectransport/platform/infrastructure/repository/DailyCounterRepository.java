package com.ectransport.platform.infrastructure.repository;

import com.ectransport.platform.infrastructure.entity.DailyCounterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface DailyCounterRepository extends JpaRepository<DailyCounterEntity, LocalDate> {
}
