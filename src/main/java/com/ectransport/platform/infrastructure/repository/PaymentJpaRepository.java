package com.ectransport.platform.infrastructure.repository;

import com.ectransport.platform.infrastructure.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, Integer> {
}
