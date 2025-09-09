package com.ectransport.platform.domain.application.mapper;

import com.ectransport.platform.domain.application.dto.*;
import com.ectransport.platform.domain.core.entity.Service;
import com.ectransport.platform.domain.core.entity.ServiceByUser;
import com.ectransport.platform.domain.core.entity.ServiceType;
import com.ectransport.platform.infrastructure.entity.ServiceOrderEntity;
import org.springframework.stereotype.Component;

@Component
public class ServiceApplicationMapper {
  public ServiceTypeDto serviceTypeToServiceTypeDto(ServiceType serviceType) {
    return ServiceTypeDto.builder()
        .id(serviceType.getId())
        .code(serviceType.getCode())
        .name(serviceType.getName())
        .build();
  }

  public CreateServiceDto createServiceToCreateServiceDto(Service service) {
    return CreateServiceDto.builder()
        .serviceId(service.getServiceNumber())
        .build();
  }

  public ServicesByUserDto serviceToServiceByUserDto(Service services) {
    return ServicesByUserDto.builder()
        .idService(services.getIdService())
        .serviceDate(services.getServiceDate())
        .serviceHour(services.getHourService())
        .destination(services.getDestination())
        .origin(services.getOrigin())
        .build();
  }

  public ServiceDto serviceByUserToServiceDto(ServiceByUser serviceByUser) {
    return ServiceDto.builder()
        .statusIdentifier(serviceByUser.getStatusIdentifier())
        .idService(serviceByUser.getIdService())
        .serviceType(serviceByUser.getServiceType())
        .serviceDate(serviceByUser.getServiceDate())
        .hourService(serviceByUser.getHourService())
        .brandVehicle(serviceByUser.getBrandVehicle())
        .origin(serviceByUser.getOrigin())
        .destination(serviceByUser.getDestination())
        .peopleNumber(serviceByUser.getPeopleNumber())
        .serviceAmmount(serviceByUser.getServiceAmmount())
        .observations(serviceByUser.getObservations())
        .userName(serviceByUser.getUserName())
        .userNumber(serviceByUser.getUserNumber())
        .userEmail(serviceByUser.getUserEmail())
        .flightNumber(serviceByUser.getFlightNumber())
        .serviceNumber(serviceByUser.getServiceNumber())
        .methodOfPayment(serviceByUser.getMethodOfPayment())
        .voucher(serviceByUser.getVoucher())
        .driverName(serviceByUser.getDriverName())
        .driverLastName(serviceByUser.getDriverLastName())
        .clientName(serviceByUser.getClientName())
        .plate(serviceByUser.getPlate())
        .statusId(serviceByUser.getStatusId())
        .driverId(serviceByUser.getDriverId())
        .userIdentification(serviceByUser.getUserIdentification())
        .clientType(serviceByUser.getClientType())
        .build();
  }

  public ServiceUpdatedDto serviceToServiceUpdatedDto(Service service){
    return ServiceUpdatedDto.builder()
        .plate(service.getPlate())
        .idDriver(service.getFkDriver())
        .build();
  }
}
