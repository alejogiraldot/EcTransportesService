package com.ectransport.platform.api;

import com.ectransport.platform.domain.application.dto.*;
import com.ectransport.platform.domain.application.ports.input.service.PaymentService;
import com.ectransport.platform.domain.application.ports.input.service.ServiceRequestService;
import com.ectransport.platform.domain.application.ports.input.service.TransportService;
import com.ectransport.platform.domain.application.ports.input.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("services")
@CrossOrigin("*")
public class ServiceController {
  private final ServiceRequestService serviceRequestService;
  private final TransportService transportService;
  private final PaymentService paymentService;
  private final VehicleService vehicleService;

  public ServiceController(ServiceRequestService serviceRequestService, TransportService transportService, PaymentService paymentService, VehicleService vehicleService) {
    this.serviceRequestService = serviceRequestService;
    this.transportService = transportService;
    this.paymentService = paymentService;
    this.vehicleService = vehicleService;
  }

  @GetMapping(value = "/service-type")
  public ResponseEntity<List<ServiceTypeDto>> getServiceTypes() {
    return ResponseEntity.ok(serviceRequestService.findAllServiceTypes());
  }

  @GetMapping("/transfer/{id}")
  public ResponseEntity<List<TransportDto>> getServiceTypes(@PathVariable("id") Integer typeId) {
    return ResponseEntity.ok(transportService.findByTypeId(typeId));
  }

  @GetMapping("/payments")
  public ResponseEntity<List<PaymentDto>> getPayments() {
    return ResponseEntity.ok(paymentService.findAllPayments());
  }

  @GetMapping("/brand")
  public ResponseEntity<List<VehicleBrandDto>> getAllVehiclesBrand() {
    return ResponseEntity.ok(vehicleService.findAllVehiclesBrand());
  }

  @GetMapping("/vehicles")
  public ResponseEntity<List<VehicleDto>> getAllVehicles() {
    return ResponseEntity.ok(vehicleService.findAllVehhicle());
  }

  @PostMapping("/create-service")
  public ResponseEntity<CreateServiceDto> saveService(@RequestBody RequestCreateServiceDto createServiceRequest){
    return ResponseEntity.ok(serviceRequestService.createService(createServiceRequest));
  }

  @PostMapping("/find-service-by-user")
  public ResponseEntity<List<ServiceDto>> saveService(@RequestBody FindServiceByUser findServiceByUser){
    return ResponseEntity.ok(serviceRequestService.findServiceByUser(findServiceByUser));
  }
  @PatchMapping("/updateStatus")
  public ResponseEntity<StatusUpdated> updateStatusService(@RequestBody UpdateStatus updateStatus){
    return ResponseEntity.ok(serviceRequestService.updateStatusService(updateStatus));
  }
}
