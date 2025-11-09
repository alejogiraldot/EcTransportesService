package com.ectransport.platform.infrastructure.repository;

import com.ectransport.platform.domain.application.ports.repository.ServiceBySettlement;
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
        so.destination_longitude as destinationLongitude,
        so.reference as reference
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
        AND (:serviceStatus IS NULL OR so.fk_service_status = :serviceStatus)
        AND (:driverId IS NULL OR so.fk_driver = :driverId)
        AND (:reference IS NULL OR LOWER(so.reference) = LOWER(:reference))
        AND (:serviceNumber IS NULL OR LOWER(so.service_number) = LOWER(:serviceNumber))
      ORDER BY so.service_date ASC, so.hour_service ASC
      """, nativeQuery = true)
  List<ServiceReport> findServiceByUser(
      @Param("initialDate") LocalDate initialDate,
      @Param("finalDate") LocalDate finalDate,
      @Param("plate") String plate,
      @Param("userId") Integer userId,
      @Param("clientId") UUID clientId,
      @Param("serviceStatus") Integer serviceStatus,
      @Param("driverId") Integer driverId,
      @Param("reference") String reference,
      @Param("serviceNumber") String serviceNumber
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
    uf.id_file as idFile,
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
        so.destination_longitude as destinationLongitude,
        so.reference as reference 
      FROM services.service_orders so
      LEFT JOIN usermanagement.users umu ON so.fk_driver = umu.id_user
      LEFT JOIN usermanagement.client uc ON so.fk_client = uc.client_id
      LEFT JOIN services.service_status ss on so.fk_service_status = ss.id
      LEFT JOIN services.transport tra on tra.id  = so.fk_transport 
      WHERE so.id_service = :transactionId
      """, nativeQuery = true)
  ServiceReport findServiceByTransactionId(
      @Param("transactionId") UUID transactionId
  );

  @Query(value = """
      SELECT
          so.service_number AS "serviceNumber",
          so.method_of_payment AS "methodOfPayment",
          uc.trade_name AS "clientName",
          ct."name" AS "clientType",
          so.origin AS "origin",
          so.hour_service AS "hourService",
          so.destination AS "destination",
          umu.name AS "driverName",
          umu.last_name AS "driverLastName",
          so.plate AS "plate",
          so.brand_vehicle AS "brand",
              (SUM(CASE WHEN uf.payment_type = 'EFECTIVO' THEN uf.amount ELSE 0 END) +
               SUM(CASE WHEN uf.payment_type = 'NO FIRMA / NO PAGA' THEN uf.amount ELSE 0 END) +
               SUM(CASE WHEN uf.payment_type = 'VOUCHER' THEN uf.amount ELSE 0 END) +
               SUM(CASE WHEN uf.payment_type = 'LINK DE PAGO' THEN uf.amount ELSE 0 END) +
               SUM(CASE WHEN uf.payment_type = 'TRANSFERENCIA' THEN uf.amount ELSE 0 END) +
               SUM(CASE WHEN uf.payment_type = 'DATÁFONO' THEN uf.amount ELSE 0 END)) AS "serviceAmmount",
          so.id_service AS "idService",
          so.service_type AS "serviceType",
          so.service_date AS "serviceDate",
          so.people_number AS "peopleNumber",
          so.observations AS "observations",
          so.user_number AS "userNumber",
          so.user_name AS "userName",
          so.user_email AS "userEmail",
          so.flight_number AS "flightNumber",
          so.voucher AS "voucher",
          ss.id_status AS "statusId",\s
          ss.id AS "statusIdentifier",
          so.fk_driver AS "driverId",
          uc.fk_client_type AS "clientType",
          umu.identification AS "userIdentification",
          tra.code AS "transportId",
          so.reference AS "reference",
          SUM(uf.beeper) AS "beeper",
          SUM(CASE WHEN uf.payment_type = 'PEAJES' THEN uf.amount ELSE 0 END) AS "tollAmount",
          SUM(CASE WHEN uf.payment_type = 'PARQUEADEROS' THEN uf.amount ELSE 0 END) AS "parking",
          SUM(CASE WHEN uf.payment_type = 'LAVADA' THEN uf.amount ELSE 0 END) AS "wash",
          SUM(CASE WHEN uf.payment_type = 'GASOLINA' THEN uf.amount ELSE 0 END) AS "gasoline",
          SUM(CASE WHEN uf.payment_type = 'EXTRAS' THEN uf.amount ELSE 0 END) AS "extra",
          SUM(CASE WHEN uf.payment_type = 'PROPINAS' THEN uf.amount ELSE 0 END) AS "tip",
          SUM(CASE WHEN uf.payment_type = 'FLYPASS' THEN uf.amount ELSE 0 END) AS "flypass",
          SUM(CASE WHEN uf.payment_type = 'LAVADA SHIP' THEN uf.amount ELSE 0 END) AS "washShip",
          SUM(CASE WHEN uf.payment_type = 'GASOLINA SHIP' THEN uf.amount ELSE 0 END) AS "gasolineShip" 
      
      FROM services.service_orders so
      LEFT JOIN usermanagement.users umu ON so.fk_driver = umu.id_user
      LEFT JOIN usermanagement.client uc ON so.fk_client = uc.client_id
      LEFT JOIN services.service_status ss ON so.fk_service_status = ss.id
      LEFT JOIN services.transport tra ON tra.id = so.fk_transport
      LEFT JOIN usermanagement.client_type ct ON uc.fk_client_type = ct.id
      LEFT JOIN services.upload_files uf ON so.id_service = uf.fk_service
      
      WHERE so.service_date >= :initialDate
        AND so.service_date <= :finalDate
        AND (:plate IS NULL OR so.plate = :plate)
        AND (:userId IS NULL OR so.fk_driver = :userId)
        AND (:clientId IS NULL OR so.fk_client = :clientId)
        AND (:serviceStatus IS NULL OR so.fk_service_status = :serviceStatus)
        AND (:driverId IS NULL OR so.fk_driver = :driverId)
      
      GROUP BY
          so.service_number,
          so.method_of_payment,
          uc.trade_name,
          ct."name",
          so.origin,
          so.hour_service,
          so.destination,
          umu.name,
          umu.last_name,
          so.plate,
          so.brand_vehicle,
          so.service_ammount,
          so.id_service,
          so.service_type,
          so.service_date,
          so.people_number,
          so.observations,
          so.user_number,
          so.user_name,
          so.user_email,
          so.flight_number,
          so.voucher,
          ss.id_status,
          ss.id,
          so.fk_driver,
          uc.fk_client_type,
          umu.identification,
          tra.code,
          so.reference
      
      ORDER BY so.service_date ASC, so.hour_service ASC;
      """, nativeQuery = true)
  List<ServiceBySettlement> findServiceBySettlement(
      @Param("initialDate") LocalDate initialDate,
      @Param("finalDate") LocalDate finalDate,
      @Param("plate") String plate,
      @Param("userId") Integer userId,
      @Param("clientId") UUID clientId,
      @Param("serviceStatus") Integer serviceStatus,
      @Param("driverId") Integer driverId
  );
}
