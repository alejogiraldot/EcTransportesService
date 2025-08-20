package com.ectransport.platform.domain.application.ports.input.service;

import com.ectransport.platform.domain.application.dto.ExpenseDto;

import java.util.List;

public interface ExpenseService {
  List<ExpenseDto> findAllExpensesType();
}
