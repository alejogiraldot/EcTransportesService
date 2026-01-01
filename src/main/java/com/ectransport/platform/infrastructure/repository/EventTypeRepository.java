package com.ectransport.platform.infrastructure.repository;

import com.ectransport.platform.infrastructure.entity.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EventTypeRepository extends JpaRepository<EventType, Integer> {
  Optional<EventType> findByCode(String code);
}
