package com.ectransport.platform.domain.application.ports.input.service;

import com.ectransport.platform.domain.application.dto.PaymentDto;

import java.util.List;

public interface PaymentService {
  List<PaymentDto> findAllPayments();
}
