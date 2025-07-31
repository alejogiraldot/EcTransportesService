package com.ectransport.platform.infrastructure.adapter;

import com.ectransport.platform.domain.application.ports.output.service.PaymentRequestRepository;
import com.ectransport.platform.domain.core.entity.Payment;
import com.ectransport.platform.infrastructure.mapper.PaymentInfrastructureMapper;
import com.ectransport.platform.infrastructure.repository.PaymentJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentJpaRepositoryImp implements PaymentRequestRepository {
  private final PaymentJpaRepository paymentJpaRepository;
  private final PaymentInfrastructureMapper paymentInfrastructureMapper;
  public PaymentJpaRepositoryImp(PaymentJpaRepository paymentJpaRepository, PaymentInfrastructureMapper paymentInfrastructureMapper) {
    this.paymentJpaRepository = paymentJpaRepository;
    this.paymentInfrastructureMapper = paymentInfrastructureMapper;
  }

  @Override
  public List<Payment> findAllPaymentOptions() {
    return paymentJpaRepository.findAll().stream().map(paymentInfrastructureMapper::paymentEntityToPayment).toList();
  }
}
