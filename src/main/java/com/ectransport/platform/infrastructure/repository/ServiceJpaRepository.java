package com.ectransport.platform.infrastructure.repository;

import com.ectransport.platform.domain.application.ports.repository.ServiceReport;
import com.ectransport.platform.domain.application.ports.repository.UploadDataService;
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
        so.fk_driver as driverId,
        uc.fk_client_type as clientType,
        umu.identification as userIdentification,
        tra.code as transportId,
        so.origin_latitude as originLatitude,
        so.origin_longitude as originLongitude,
        so.destination_latitude as destinationLatitude,
        so.destination_longitude as destinationLongitude
      FROM services.service_orders so
      LEFT JOIN usermanagement.users umu ON so.fk_driver = umu.id_user
      LEFT JOIN usermanagement.client uc ON so.fk_client = uc.client_id
      LEFT JOIN services.service_status ss on so.fk_service_status = ss.id
      LEFT JOIN services.transport tra on tra.id  = so.fk_transport 
      WHERE so.service_date >= :initialDate
        AND so.service_date <= :finalDate
        AND (:plate IS NULL OR so.plate = :plate)
        AND (:userId IS NULL OR so.fk_driver = :userId)
        AND (:clientId IS NULL OR so.fk_client = :clientId)
      ORDER BY so.service_date ASC, so.hour_service ASC
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

  @Modifying
  @Transactional
  @Query(value = """
      UPDATE services.service_orders
      SET fk_driver = :idDriver,
          plate = :plate,
          fk_service_status = :statusId
      WHERE id_service = :idService
      """, nativeQuery = true)
  int updateDriverByService(
      @Param("idService") UUID idService,
      @Param("idDriver") Integer idDriver,
      @Param("plate") String plate,
      @Param("statusId") Integer statusId
  );


  @Query(value = """
    select
    uf.fk_type_upload as fkTypeUpload,
    uf.beeper as beeper,
    uf.amount as amount,
    uf.description as description,
    uf.payment_type as paymentType,
    uf.route as route,
    uf.file_name as fileName,
    cli.legal_name as legalName,
    cli.fk_client_type as clientType,
    sos.plate as plate,
    sos.service_ammount as serviceAmount,
    us.name as name,
    us.last_name as lastname
    from services.service_orders sos
    join services.upload_files uf on sos.id_service = uf.fk_service
    join usermanagement.client cli on cli.client_id = sos.fk_client\s
    join usermanagement.users us on us.id_user = sos.fk_driver
    where lower(sos.service_number) = lower(:serviceNumber)
      """, nativeQuery = true)
  List<UploadDataService> getUploadDataService(@Param("serviceNumber") String serviceNumber);


  @Query(value = """
        SELECT COUNT(*) 
        FROM services.service_orders s
        WHERE s.service_date = :date
      """, nativeQuery = true)
  int serviceByDay(@Param("date") LocalDate date);

  @Query(value = """
        SELECT COUNT(*) 
        FROM services.service_orders s
        WHERE s.service_date = :date
      """, nativeQuery = true)
  int usersInService(@Param("date") LocalDate date);
}
