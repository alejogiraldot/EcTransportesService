package com.ectransport.platform.domain.application.ports.output.service;

import com.ectransport.platform.domain.core.entity.Transport;

import java.util.List;

public interface TransportRequestRepository {
  List<Transport> findById(Integer id);
}
