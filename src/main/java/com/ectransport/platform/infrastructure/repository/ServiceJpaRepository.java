package com.ectransport.platform.infrastructure.repository;

import com.ectransport.platform.domain.application.ports.repository.ServiceReport;
import com.ectransport.platform.infrastructure.entity.ServiceOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ServiceJpaRepository extends JpaRepository<ServiceOrderEntity, UUID> {

  @Query(value = """
      SELECT 
        so.id_service  as idService,
        so.service_type as serviceType,
        so.service_date as serviceDate,
        so.hour_service as hourService,
        so.brand_vehicle as brandVehicle,
        so.origin as origin,
        so.destination as destination,
        so.people_number as peopleNumber,
        so.service_ammount as serviceAmmount,
        so.observations as observations,
        so.user_number as userNumber,
        so.user_name as userName,
        so.user_email as userEmail,
        so.flight_number as flightNumber,
        so.method_of_payment as methodOfPayment,
        so.voucher as voucher,
        so.plate as plate,
        so.service_number as serviceNumber,
        umu.name as driverName,
        umu.last_name as driverLastName,
        uc.trade_name as clientName,
        ss.id_status as statusId, 
        ss.id as statusIdentifier,
        so.fk_driver as driverId 
      FROM services.service_orders so
      LEFT JOIN usermanagement.users umu ON so.fk_driver = umu.id_user
      LEFT JOIN usermanagement.client uc ON so.fk_client = uc.client_id
      LEFT JOIN services.service_status ss on so.fk_service_status = ss.id
      WHERE so.service_date >= :initialDate
        AND so.service_date <= :finalDate
        AND (:plate IS NULL OR so.plate = :plate)
        AND (:userId IS NULL OR so.fk_driver = :userId)
        AND (:clientId IS NULL OR so.fk_client = :clientId)
      """, nativeQuery = true)
  List<ServiceReport> findServiceByUser(
      @Param("initialDate") LocalDate initialDate,
      @Param("finalDate") LocalDate finalDate,
      @Param("plate") String plate,
      @Param("userId") Integer userId,
      @Param("clientId") UUID clientId
  );


  @Modifying
  @Transactional
  @Query(value = """
      UPDATE services.service_orders
      SET fk_service_status = :newStatusId
      WHERE id_service = :idService
      """, nativeQuery = true)
  int updateServiceStatus(@Param("idService") UUID idService, @Param("newStatusId") Integer newStatusId);


}
