package com.ectransport.platform.api;

import com.ectransport.platform.domain.application.dto.*;
import com.ectransport.platform.domain.application.ports.input.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("services")
@CrossOrigin("*")
@Slf4j
public class ServiceController {
  private final ServiceRequestService serviceRequestService;
  private final TransportService transportService;
  private final PaymentService paymentService;
  private final VehicleService vehicleService;
  private final ExpenseService expenseService;

  public ServiceController(ServiceRequestService serviceRequestService, TransportService transportService, PaymentService paymentService, VehicleService vehicleService, ExpenseService expenseService) {
    this.serviceRequestService = serviceRequestService;
    this.transportService = transportService;
    this.paymentService = paymentService;
    this.vehicleService = vehicleService;
    this.expenseService = expenseService;
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
  public ResponseEntity<CreateServiceDto> saveService(@RequestBody RequestCreateServiceDto createServiceRequest) {
    return ResponseEntity.ok(serviceRequestService.createService(createServiceRequest));
  }

  @PostMapping("/find-service-by-user")
  public ResponseEntity<List<ServiceDto>> saveService(@RequestBody FindServiceByUser findServiceByUser) {
    return ResponseEntity.ok(serviceRequestService.findServiceByUser(findServiceByUser));
  }

  @PatchMapping("/update-status")
  public ResponseEntity<StatusUpdatedDto> updateStatusService(@RequestBody UpdateStatusDto updateStatusDto) {
    return ResponseEntity.ok(serviceRequestService.updateStatusService(updateStatusDto));
  }

  @PatchMapping("/update-driver")
  public ResponseEntity<ServiceUpdatedDto> updateDriverByService(@RequestBody UpdateDriverDto updateDriverDto) {
    return ResponseEntity.ok(serviceRequestService.updateDriverByService(updateDriverDto));
  }

  @PostMapping(value = "/upload")
  public ResponseEntity<List<FileUploadResponseDto>> upload(
      @RequestBody CombinedUploadRequestDto requestBody
  ) throws IOException {
    List<FileUploadResponseDto> result = serviceRequestService.uploadDocument(
        requestBody.getIdentification(),
        requestBody.getUploadData(),
        requestBody.getExpenseDataUpload(),
        requestBody.getFkService()
    );
    return ResponseEntity.ok(result);
  }

  @GetMapping("/expense")
  public ResponseEntity<List<ExpenseDto>> getAllExpenseType() {
    return ResponseEntity.ok(expenseService.findAllExpensesType());
  }

  @PostMapping(value = "/download-data")
  public ResponseEntity<List<FileInfoByServiceDto>> downloadService(
      @RequestBody UploadData uploadData
  ) throws IOException {
    List<FileInfoByServiceDto> result = serviceRequestService.downloadDocument(uploadData.getServiceNumber());
    return ResponseEntity.ok(result);
  }

  @GetMapping("/service-by-day")
  public ResponseEntity<ServiceByDayDto> getServiceByDay() {
    return ResponseEntity.ok(serviceRequestService.getServiceByDay());
  }
}
