package com.ectransport.platform.domain.application.adapter;

import com.ectransport.platform.domain.application.dto.ExpenseDto;
import com.ectransport.platform.domain.application.mapper.ExpenseApplicationMapper;
import com.ectransport.platform.domain.application.ports.input.service.ExpenseService;
import com.ectransport.platform.domain.application.ports.output.service.ExpenseTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImp implements ExpenseService {

  private final ExpenseTypeRepository expenseTypeRepository;
  private final ExpenseApplicationMapper expenseApplicationMapper;

  public ExpenseServiceImp(ExpenseTypeRepository expenseTypeRepository, ExpenseApplicationMapper expenseApplicationMapper) {
    this.expenseTypeRepository = expenseTypeRepository;
    this.expenseApplicationMapper = expenseApplicationMapper;
  }

  @Override
  public List<ExpenseDto> findAllExpensesType() {
    return expenseTypeRepository.findAllExpenseType().stream().map(expenseApplicationMapper::expenseToExpenseDto).toList();
  }
}
