package com.ectransport.platform.api;

import com.ectransport.platform.domain.application.dto.*;
import com.ectransport.platform.domain.application.ports.input.service.*;
import com.ectransport.platform.domain.core.constans.AuxiliarConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
  private final UploadService uploadService;

  public ServiceController(ServiceRequestService serviceRequestService, TransportService transportService, PaymentService paymentService, VehicleService vehicleService, ExpenseService expenseService, UploadService uploadService) {
    this.serviceRequestService = serviceRequestService;
    this.transportService = transportService;
    this.paymentService = paymentService;
    this.vehicleService = vehicleService;
    this.expenseService = expenseService;
    this.uploadService = uploadService;
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
  public ResponseEntity<List<VehicleDto>> getAllVehicles(@RequestParam(required = false) String status)  {
    return ResponseEntity.ok(vehicleService.findAllVehhicle(status));
  }

  @PostMapping("/create-service")
  public ResponseEntity<CreateServiceDto> saveService(@RequestBody RequestCreateServiceDto createServiceRequest) {
    return ResponseEntity.ok(serviceRequestService.createService(createServiceRequest));
  }

  @PostMapping("/find-service-by-user")
  public ResponseEntity<List<ServiceDto>> findServiceByUser(@RequestBody FindServiceByUser findServiceByUser) {
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

  @GetMapping("/service-by-number/{transactionId}")
  public ResponseEntity<ServiceDto> getServiceTypes(@PathVariable("transactionId") UUID transactionId) {
    return ResponseEntity.ok(serviceRequestService.findServiceByTransactionId(transactionId));
  }

  @PostMapping("/find-service-by-settlement")
  public ResponseEntity<List<ServiceBySettlementDto>> findServiceBySettlement(@RequestBody FindServiceByUser findServiceByUser) {
    return ResponseEntity.ok(serviceRequestService.findServiceBySettlement(findServiceByUser));
  }
  @PostMapping("/update-settlement")
  public ResponseEntity<List<FileUploadResponseDto>> uploadSettlement(
      @RequestBody CombinedUploadRequestDto requestBody
  ) throws IOException {
    List<FileUploadResponseDto> result = uploadService.uploadSettlement(
        requestBody.getIdentification(),
        requestBody.getUploadData(),
        requestBody.getExpenseDataUpload(),
        requestBody.getFkService()
    );
    return ResponseEntity.ok(result);
  }

  @PostMapping("/edit-service")
  public ResponseEntity<CreateServiceDto> editService(@RequestBody RequestCreateServiceDto createServiceRequest) {
    return ResponseEntity.ok(serviceRequestService.editService(createServiceRequest));
  }

  @GetMapping("/service-number")
  public ResponseEntity<ServiceNumberDto> getServiceNumber() {
    return ResponseEntity.ok(serviceRequestService.serviceNumber());
  }

  @PostMapping("/create-vehicle")
  public ResponseEntity<VehicleDto> createVehicle(@RequestBody CreateVehicleDto createVehicleDto) {
    return ResponseEntity.ok(vehicleService.createVehicle(createVehicleDto));
  }


  @PostMapping("/edit-vehicle")
  public ResponseEntity<VehicleDto> editVehicle(@RequestBody UpdateVehicleDto updateVehicleDto) {
    return ResponseEntity.ok(vehicleService.updateVehicle(updateVehicleDto));
  }

  @PutMapping(value = "/update-status-vehicle")
  public ResponseEntity<Map<String, String>> updateStatusClient(@RequestBody UpdateStatusVehicleDto updateStatusVehicleDto) {
    try {
      vehicleService.updateVehicleStatus(updateStatusVehicleDto);
      Map<String, String> response = new HashMap<>();
      response.put(AuxiliarConstants.MESSAGE_RESPONSE, AuxiliarConstants.UPDATED_STATUS);
      response.put(AuxiliarConstants.STATUS, updateStatusVehicleDto.getStatus());
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      Map<String, String> errorResponse = new HashMap<>();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
  }

  @GetMapping("/requeriments")
  public ResponseEntity<List<RequerimentsDto>> requeriments() {
    return ResponseEntity.ok(serviceRequestService.getRequeriments());
  }


  @GetMapping("/find-history/{id}")
  public ResponseEntity<List<HistoryData>> getHistory(@PathVariable("id") UUID serviceId) {
    return ResponseEntity.ok(serviceRequestService.getHistoryById(serviceId));
  }
}
