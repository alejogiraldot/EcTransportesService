package com.ectransport.platform.infrastructure.mapper;

import com.ectransport.platform.domain.core.entity.Payment;
import com.ectransport.platform.infrastructure.entity.PaymentEntity;
import org.springframework.stereotype.Component;

@Component
public class PaymentInfrastructureMapper {

  public Payment paymentEntityToPayment(PaymentEntity paymentEntity) {
    return Payment.builder()
        .id(paymentEntity.getId())
        .code(paymentEntity.getCode())
        .name(paymentEntity.getName())
        .build();
  }
}
