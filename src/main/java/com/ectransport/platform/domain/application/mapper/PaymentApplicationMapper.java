package com.ectransport.platform.domain.application.mapper;

import com.ectransport.platform.domain.application.dto.PaymentDto;
import com.ectransport.platform.domain.core.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentApplicationMapper {

  public PaymentDto paymentToPaymentDto(Payment payment) {
    return PaymentDto.builder()
        .id(payment.getId())
        .code(payment.getCode())
        .name(payment.getName())
        .build();
  }
}
