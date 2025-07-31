package com.ectransport.platform.domain.application.adapter;

import com.ectransport.platform.domain.application.dto.PaymentDto;
import com.ectransport.platform.domain.application.mapper.PaymentApplicationMapper;
import com.ectransport.platform.domain.application.ports.input.service.PaymentService;
import com.ectransport.platform.domain.application.ports.output.service.PaymentRequestRepository;
import com.ectransport.platform.domain.core.entity.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImp implements PaymentService {
  private final PaymentRequestRepository paymentRequestRepository;
  private final PaymentApplicationMapper paymentApplicationMapper;

  public PaymentServiceImp(PaymentRequestRepository paymentRequestRepository, PaymentApplicationMapper paymentApplicationMapper) {
    this.paymentRequestRepository = paymentRequestRepository;
    this.paymentApplicationMapper = paymentApplicationMapper;
  }

  @Override
  public List<PaymentDto> findAllPayments() {
    return paymentRequestRepository.findAllPaymentOptions().stream().map(paymentApplicationMapper::paymentToPaymentDto).toList();
  }
}
