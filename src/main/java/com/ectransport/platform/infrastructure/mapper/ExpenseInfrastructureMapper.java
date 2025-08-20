package com.ectransport.platform.infrastructure.mapper;

import com.ectransport.platform.domain.core.entity.Expense;
import com.ectransport.platform.infrastructure.entity.ExpenseEntity;
import org.springframework.stereotype.Component;

@Component
public class ExpenseInfrastructureMapper {
  public Expense expenseEntityToExpense(ExpenseEntity expenseEntity) {
    return Expense.builder()
        .id(expenseEntity.getId())
        .code(expenseEntity.getCode())
        .name(expenseEntity.getName())
        .build();
  }
}
