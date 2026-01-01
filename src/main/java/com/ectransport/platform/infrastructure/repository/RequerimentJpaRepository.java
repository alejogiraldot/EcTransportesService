package com.ectransport.platform.infrastructure.repository;

import com.ectransport.platform.infrastructure.entity.Requeriment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequerimentJpaRepository  extends JpaRepository<Requeriment, Integer> {
}
