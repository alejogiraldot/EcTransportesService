package com.ectransport.platform.infrastructure.adapter;

import com.ectransport.platform.domain.application.ports.output.service.ExpenseTypeRepository;
import com.ectransport.platform.domain.core.entity.Expense;
import com.ectransport.platform.infrastructure.mapper.ExpenseInfrastructureMapper;
import com.ectransport.platform.infrastructure.repository.ExpenseJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExpenseTypeJpaRepositoryImpl implements ExpenseTypeRepository {

  private final ExpenseJpaRepository expenseJpaRepository;
  private final ExpenseInfrastructureMapper expenseInfrastructureMapper;

  public ExpenseTypeJpaRepositoryImpl(ExpenseJpaRepository expenseJpaRepository, ExpenseInfrastructureMapper expenseInfrastructureMapper) {
    this.expenseJpaRepository = expenseJpaRepository;
    this.expenseInfrastructureMapper = expenseInfrastructureMapper;
  }

  @Override
  public List<Expense> findAllExpenseType() {
    return expenseJpaRepository.findAll().stream().map(expenseInfrastructureMapper::expenseEntityToExpense).toList();
  }
}
