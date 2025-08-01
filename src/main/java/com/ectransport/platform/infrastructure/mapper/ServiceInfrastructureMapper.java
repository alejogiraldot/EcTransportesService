package com.ectransport.platform.infrastructure.mapper;

import com.ectransport.platform.domain.application.dto.RequestCreateServiceDto;
import com.ectransport.platform.domain.core.entity.CreateService;
import com.ectransport.platform.domain.core.entity.ServiceType;
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
        .branVehicle(requestCreateServiceDto.getBranVehicle())
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
        .serviceNumber(requestCreateServiceDto.getServiceNumber())
        .build();
  }

  public CreateService serviceEntityToCreateService(ServiceOrderEntity serviceOrderEntity) {
    return null;
  }
}
