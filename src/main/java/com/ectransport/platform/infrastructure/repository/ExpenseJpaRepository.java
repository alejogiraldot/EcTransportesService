package com.ectransport.platform.infrastructure.repository;

import com.ectransport.platform.infrastructure.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseJpaRepository extends JpaRepository<ExpenseEntity,Integer> {
}
