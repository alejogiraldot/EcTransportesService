package com.ectransport.platform.domain.application.ports.output.service;

import com.ectransport.platform.domain.core.entity.Expense;

import java.util.List;

public interface ExpenseTypeRepository {
  List<Expense> findAllExpenseType();
}
