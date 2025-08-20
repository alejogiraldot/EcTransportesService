package com.ectransport.platform.domain.application.mapper;

import com.ectransport.platform.domain.application.dto.ExpenseDto;
import com.ectransport.platform.domain.core.entity.Expense;
import org.springframework.stereotype.Component;

@Component
public class ExpenseApplicationMapper {
  public ExpenseDto expenseToExpenseDto(Expense expense) {
    return ExpenseDto.builder()
        .id(expense.getId())
        .code(expense.getCode())
        .name(expense.getName())
        .build();
  }
}
