package com.ectransport.platform.api;

import com.ectransport.platform.domain.application.dto.PaymentDto;
import com.ectransport.platform.domain.application.dto.ServiceTypeDto;
import com.ectransport.platform.domain.application.dto.TransportDto;
import com.ectransport.platform.domain.application.dto.VehicleBrandDto;
import com.ectransport.platform.domain.application.ports.input.service.PaymentService;
import com.ectransport.platform.domain.application.ports.input.service.ServiceTypeService;
import com.ectransport.platform.domain.application.ports.input.service.TransportService;
import com.ectransport.platform.domain.application.ports.input.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("services")
@CrossOrigin("*")
public class ServiceController {
  private final ServiceTypeService serviceTypeService;
  private final TransportService transportService;
  private final PaymentService paymentService;
  private final VehicleService vehicleService;
  public ServiceController(ServiceTypeService serviceTypeService, TransportService transportService, PaymentService paymentService, VehicleService vehicleService) {
    this.serviceTypeService = serviceTypeService;
    this.transportService = transportService;
    this.paymentService = paymentService;
    this.vehicleService = vehicleService;
  }

  @GetMapping(value = "/service-type")
  public ResponseEntity<List<ServiceTypeDto>> getServiceTypes() {
    return ResponseEntity.ok(serviceTypeService.findAllServiceTypes());
  }

  @GetMapping("/transfer/{id}")
  public ResponseEntity<List<TransportDto>> getServiceTypes(@PathVariable("id") Integer typeId) {
    return ResponseEntity.ok(transportService.findByTypeId(typeId));
  }
  @GetMapping("/payments")
  public ResponseEntity<List<PaymentDto>> getPayments() {
    return ResponseEntity.ok(paymentService.findAllPayments());
  }
  @GetMapping("/vehicles")
  public ResponseEntity<List<VehicleBrandDto>> getAllVehiclesBrand() {
    return ResponseEntity.ok(vehicleService.findAllVehiclesBrand());
  }
}
