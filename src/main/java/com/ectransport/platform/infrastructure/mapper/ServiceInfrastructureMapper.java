package com.ectransport.platform.infrastructure.mapper;

import com.ectransport.platform.domain.application.dto.RequestCreateServiceDto;
import com.ectransport.platform.domain.application.ports.repository.ServiceReport;
import com.ectransport.platform.domain.core.entity.DailyCounter;
import com.ectransport.platform.domain.core.entity.Service;
import com.ectransport.platform.domain.core.entity.ServiceByUser;
import com.ectransport.platform.domain.core.entity.ServiceType;
import com.ectransport.platform.infrastructure.entity.DailyCounterEntity;
import com.ectransport.platform.infrastructure.entity.ServiceOrderEntity;
import com.ectransport.platform.infrastructure.entity.ServiceTypeEntity;
import org.springframework.stereotype.Component;

@Component
public class ServiceInfrastructureMapper {

  public ServiceType serviceTypeEntityToServiceType(ServiceTypeEntity entity) {
    return ServiceType.builder()
        .id(entity.getId())
        .code(entity.getCode())
        .name(entity.getName())
        .build();
  }

  public ServiceOrderEntity requestCreateServiceDtoToServiceOrderEntity(RequestCreateServiceDto requestCreateServiceDto) {
    return ServiceOrderEntity.builder()
        .idService(requestCreateServiceDto.getIdService())
        .serviceType(requestCreateServiceDto.getServiceType())
        .fkClient(requestCreateServiceDto.getFkClient())
        .fkClientType(requestCreateServiceDto.getFkClientType())
        .serviceDate(requestCreateServiceDto.getServiceDate())
        .hourService(requestCreateServiceDto.getHourService())
        .branVehicle(requestCreateServiceDto.getBrandVehicle())
        .fkTransport(requestCreateServiceDto.getFkTransport())
        .origin(requestCreateServiceDto.getOrigin())
        .destination(requestCreateServiceDto.getDestination())
        .peopleNumber(requestCreateServiceDto.getPeopleNumber())
        .serviceAmmount(requestCreateServiceDto.getServiceAmount())
        .observations(requestCreateServiceDto.getObservations())
        .userName(requestCreateServiceDto.getUserName())
        .userNumber(requestCreateServiceDto.getUserNumber())
        .userEmail(requestCreateServiceDto.getUserEmail())
        .creationService(requestCreateServiceDto.getCreationService())
        .methodOfPayment(requestCreateServiceDto.getMethodOfPayment())
        .voucher(requestCreateServiceDto.getVoucher())
        .fkDriver(requestCreateServiceDto.getFkDriver())
        .plate(requestCreateServiceDto.getPlate())
        .serviceDate(requestCreateServiceDto.getServiceDate())
        .flightNumber(requestCreateServiceDto.getFlightNumber())
        .serviceNumber(requestCreateServiceDto.getServiceNumber())
        .fkTransport(requestCreateServiceDto.getFkTransport())
        .fkServiceStatus(requestCreateServiceDto.getStatus())
        .originLatitude(requestCreateServiceDto.getOriginLatitude())
        .originLongitude(requestCreateServiceDto.getOriginLongitude())
        .destinationLatitude(requestCreateServiceDto.getDestinationLatitude())
        .destinationLongitude(requestCreateServiceDto.getDestinationLongitude())
        .build();
  }

  public Service serviceEntityToService(ServiceOrderEntity serviceOrderEntity) {
    return Service.builder()
        .fkDriver(serviceOrderEntity.getFkDriver())
        .serviceNumber(serviceOrderEntity.getServiceNumber())
        .hourService(serviceOrderEntity.getHourService())
        .serviceDate(serviceOrderEntity.getServiceDate())
        .origin(serviceOrderEntity.getOrigin())
        .destination(serviceOrderEntity.getDestination())
        .idService(serviceOrderEntity.getIdService())
        .plate(serviceOrderEntity.getPlate())
        .build();
  }

  public DailyCounter dailyCounterEntityToDailyCounter(DailyCounterEntity dailyCounterEntity) {
    return DailyCounter.builder()
        .date(dailyCounterEntity.getDate())
        .counter(dailyCounterEntity.getCounter())
        .build();
  }

  public DailyCounterEntity dailyCounterToDailyCounterDto(DailyCounter dailyCounter) {
    return DailyCounterEntity.builder()
        .counter(dailyCounter.getCounter())
        .date(dailyCounter.getDate())
        .build();
  }

  public ServiceByUser serviceReportToService(ServiceReport serviceReport) {
    return ServiceByUser.builder()
        .statusIdentifier(serviceReport.getStatusIdentifier())
        .idService(serviceReport.getIdService())
        .serviceType(serviceReport.getServiceType())
        .serviceDate(serviceReport.getServiceDate())
        .hourService(serviceReport.getHourService())
        .brandVehicle(serviceReport.getBrandVehicle())
        .origin(serviceReport.getOrigin())
        .destination(serviceReport.getDestination())
        .peopleNumber(serviceReport.getPeopleNumber())
        .serviceAmmount(serviceReport.getServiceAmmount())
        .observations(serviceReport.getObservations())
        .userName(serviceReport.getUserName())
        .userNumber(serviceReport.getUserNumber())
        .userEmail(serviceReport.getUserEmail())
        .flightNumber(serviceReport.getFlightNumber())
        .serviceNumber(serviceReport.getServiceNumber())
        .methodOfPayment(serviceReport.getMethodOfPayment())
        .voucher(serviceReport.getVoucher())
        .plate(serviceReport.getPlate())
        .driverName(serviceReport.getDriverName())
        .driverLastName(serviceReport.getDriverLastName())
        .clientName(serviceReport.getClientName())
        .statusId(serviceReport.getStatusId())
        .driverId(serviceReport.getDriverId())
        .userIdentification(serviceReport.getUserIdentification())
        .clientType(serviceReport.getClientType())
        .transportId(serviceReport.getTransportId())
        .originLatitude(serviceReport.getOriginLatitude())
        .originLongitude(serviceReport.getOriginLongitude())
        .destinationLatitude(serviceReport.getDestinationLatitude())
        .destinationLongitude(serviceReport.getDestinationLongitude())
        .build();
    }
}
