package com.ectransport.platform.domain.application.ports.output.service;

import com.ectransport.platform.domain.core.entity.Payment;

import java.util.List;

public interface PaymentRequestRepository {
  List<Payment> findAllPaymentOptions();
}
