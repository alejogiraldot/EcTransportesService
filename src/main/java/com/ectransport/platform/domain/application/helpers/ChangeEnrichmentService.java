package com.ectransport.platform.domain.application.helpers;

import com.ectransport.platform.domain.application.dto.ClientDto;
import com.ectransport.platform.domain.application.dto.UserDto;
import com.ectransport.platform.domain.application.security.JwtTokenInterceptor;
import com.ectransport.platform.domain.application.security.JwtTokenProvider;
import com.ectransport.platform.infrastructure.adapter.TransportJpaRequestRepositoryImp;
import com.ectransport.platform.infrastructure.repository.RequerimentJpaRepository;
import com.ectransport.platform.infrastructure.repository.ServiceStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Slf4j
public class ChangeEnrichmentService {

  private final RestTemplate restTemplate;
  private final JwtTokenInterceptor jwtTokenInterceptor;
  private final JwtTokenProvider tokenProvider;
  private final TransportJpaRequestRepositoryImp transportRepository; // NUEVO
  private final RequerimentJpaRepository requerimentRepository; // NUEVO
  private final ServiceStatusRepository serviceStatusRepository; // ✅ NUEVO

  @Value("${get.driver.url}")
  private String driverServiceUrl;

  @Value("${get.client.url}")
  private String clientServiceUrl;

  public ChangeEnrichmentService(
      RestTemplate restTemplate,
      JwtTokenInterceptor jwtTokenInterceptor,
      JwtTokenProvider tokenProvider, TransportJpaRequestRepositoryImp transportRepository, RequerimentJpaRepository requerimentRepository, ServiceStatusRepository serviceStatusRepository) {
    this.restTemplate = restTemplate;
    this.jwtTokenInterceptor = jwtTokenInterceptor;
    this.tokenProvider = tokenProvider;
    this.transportRepository = transportRepository;
    this.requerimentRepository = requerimentRepository;
    this.serviceStatusRepository = serviceStatusRepository;
  }


  public void enrichChanges(Map<String, Map<String, Object>> changes) {
    executeWithToken(() -> {
      if (changes.containsKey("fkClient")) {
        enrichClientChange(changes.get("fkClient"));
      }
      if (changes.containsKey("fkDriver")) {
        enrichDriverChange(changes.get("fkDriver"));
      }
      if (changes.containsKey("fkTransport")) {
        enrichTransportChange(changes.get("fkTransport"));
      }
      if (changes.containsKey("requirements")) {
        enrichRequirementsChange(changes.get("requirements"));
      }
      if (changes.containsKey("fkServiceStatus")) {
        log.info("Enriqueciendo fkServiceStatus (BD local)...");
        enrichServiceStatusChange(changes.get("fkServiceStatus"));
      }
    });
  }

  private void executeWithToken(Runnable operation) {
    Optional<String> token = tokenProvider.getCurrentToken();

    if (token.isEmpty()) {
      log.warn("No se pudo obtener el token JWT. No se enriquecerán los cambios.");
      return;
    }

    jwtTokenInterceptor.setToken(token.get());
    try {
      operation.run();
    } finally {
      jwtTokenInterceptor.setToken(null);
    }
  }

  private void enrichClientChange(Map<String, Object> change) {
    Object initialValue = change.get("initialValue");
    Object lastValue = change.get("lastValue");

    log.debug("Enriqueciendo cambio de cliente. Initial: {}, Last: {}", initialValue, lastValue);
    if (isValidUUID(initialValue)) {
      try {
        UUID clientId = UUID.fromString(initialValue.toString());
        fetchClientName(clientId).ifPresent(name -> {
          change.put("initialValue", name);
          log.info("Cliente anterior enriquecido: {}", name);
        });
      } catch (IllegalArgumentException e) {
        log.warn("UUID inválido para cliente inicial: {}", initialValue);
      }
    }

    if (isValidUUID(lastValue)) {
      try {
        UUID clientId = UUID.fromString(lastValue.toString());
        fetchClientName(clientId).ifPresent(name -> {
          change.put("lastValue", name);
          log.info("Cliente nuevo enriquecido: {}", name);
        });
      } catch (IllegalArgumentException e) {
        log.warn("UUID inválido para cliente nuevo: {}", lastValue);
      }
    }
  }

  private void enrichDriverChange(Map<String, Object> change) {
    Object initialValue = change.get("initialValue");
    Object lastValue = change.get("lastValue");

    log.debug("Enriqueciendo cambio de conductor. Initial: {}, Last: {}", initialValue, lastValue);

    if (isValidInteger(initialValue)) {
      try {
        Integer driverId = Integer.parseInt(initialValue.toString());
        fetchDriverName(driverId).ifPresent(name -> {
          change.put("initialValue", name);
          log.info("Conductor anterior enriquecido: {}", name);
        });
      } catch (NumberFormatException e) {
        log.warn("ID de conductor inválido: {}", initialValue);
      }
    }

    if (isValidInteger(lastValue)) {
      try {
        Integer driverId = Integer.parseInt(lastValue.toString());
        fetchDriverName(driverId).ifPresent(name -> {
          change.put("lastValue", name);
          log.info("Conductor nuevo enriquecido: {}", name);
        });
      } catch (NumberFormatException e) {
        log.warn("ID de conductor inválido: {}", lastValue);
      }
    }
  }

  private boolean isValidUUID(Object value) {
    if (value == null || value.toString().isEmpty()) {
      return false;
    }
    try {
      UUID.fromString(value.toString());
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }

  private boolean isValidInteger(Object value) {
    if (value == null || value.toString().isEmpty()) {
      return false;
    }
    try {
      Integer.parseInt(value.toString());
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  private Optional<String> fetchClientName(UUID clientId) {
    try {
      String requestUrl = clientServiceUrl + clientId;
      log.debug("Consultando cliente en: {}", requestUrl);

      ResponseEntity<ClientDto> response = restTemplate.exchange(
          requestUrl,
          HttpMethod.GET,
          null,
          new ParameterizedTypeReference<>() {}
      );

      if (response.getBody() != null) {
        String tradeName = response.getBody().getTradeName();
        log.info("Cliente encontrado: {}", tradeName);
        return Optional.of(tradeName);
      }

      log.warn("Cliente no encontrado con ID: {}", clientId);
      return Optional.empty();

    } catch (RestClientException e) {
      log.error("Error al consultar cliente con ID {}: {}", clientId, e.getMessage());
      return Optional.empty();
    }
  }

  /**
   * Obtiene el nombre completo del conductor desde el servicio externo
   */
  private Optional<String> fetchDriverName(Integer driverId) {
    try {
      String requestUrl = driverServiceUrl + driverId;
      log.debug("Consultando conductor en: {}", requestUrl);

      ResponseEntity<UserDto> response = restTemplate.exchange(
          requestUrl,
          HttpMethod.GET,
          null,
          new ParameterizedTypeReference<>() {}
      );

      if (response.getBody() != null) {
        UserDto driver = response.getBody();
        String fullName = driver.getName() + " " + driver.getLastName();
        log.info("Conductor encontrado: {}", fullName);
        return Optional.of(fullName);
      }

      log.warn("Conductor no encontrado con ID: {}", driverId);
      return Optional.empty();

    } catch (RestClientException e) {
      log.error("Error al consultar conductor con ID {}: {}", driverId, e.getMessage());
      return Optional.empty();
    }
  }

  private void enrichTransportChange(Map<String, Object> change) {
    Object initialValue = change.get("initialValue");
    Object lastValue = change.get("lastValue");

    log.debug("Enriqueciendo cambio de transporte. Initial: {}, Last: {}", initialValue, lastValue);

    if (isValidInteger(initialValue)) {
      try {
        Integer transportId = Integer.parseInt(initialValue.toString());
        fetchTransportName(transportId).ifPresent(name -> {
          change.put("initialValue", name);
          log.info("Transporte anterior enriquecido: {}", name);
        });
      } catch (NumberFormatException e) {
        log.warn("ID de transporte inválido: {}", initialValue);
      }
    }
    if (isValidInteger(lastValue)) {
      try {
        Integer transportId = Integer.parseInt(lastValue.toString());
        fetchTransportName(transportId).ifPresent(name -> {
          change.put("lastValue", name);
          log.info("Transporte nuevo enriquecido: {}", name);
        });
      } catch (NumberFormatException e) {
        log.warn("ID de transporte inválido: {}", lastValue);
      }
    }
  }


  private Optional<String> fetchTransportName(Integer transportId) {
    try {
      log.debug("Consultando transporte en BD local con ID: {}", transportId);
      return transportRepository.findTransportById(transportId)
          .map(transport -> {
            String name = transport.getName();
            log.info("Transporte encontrado: {}", name);
            return name;
          });

    } catch (Exception e) {
      log.error("Error al consultar transporte con ID {}: {}", transportId, e.getMessage());
      return Optional.empty();
    }
  }

  private void enrichRequirementsChange(Map<String, Object> change) {
    Object initialValue = change.get("initialValue");
    Object lastValue = change.get("lastValue");

    log.debug("Enriqueciendo cambio de requerimientos. Initial: {}, Last: {}", initialValue, lastValue);

    // Enriquecer valor inicial
    if (initialValue != null && !initialValue.toString().equals("Sin requerimientos")) {
      String enrichedInitial = enrichRequirementsList(initialValue.toString());
      change.put("initialValue", enrichedInitial);
    }

    // Enriquecer valor nuevo
    if (lastValue != null && !lastValue.toString().equals("Sin requerimientos")) {
      String enrichedLast = enrichRequirementsList(lastValue.toString());
      change.put("lastValue", enrichedLast);
    }
  }

  private String enrichRequirementsList(String requirementsStr) {
    try {
      // Extraer IDs: "[1, 2, 3]" → [1, 2, 3]
      String cleaned = requirementsStr.replaceAll("[\\[\\]\\s]", "");
      if (cleaned.isEmpty()) {
        return "Sin requerimientos";
      }

      String[] ids = cleaned.split(",");
      List<String> names = new ArrayList<>();

      for (String idStr : ids) {
        try {
          Integer reqId = Integer.parseInt(idStr.trim());
          fetchRequirementName(reqId).ifPresent(names::add);
        } catch (NumberFormatException e) {
          log.warn("ID de requerimiento inválido: {}", idStr);
        }
      }

      return names.isEmpty() ? "Sin requerimientos" : String.join(", ", names);

    } catch (Exception e) {
      log.error("Error procesando lista de requerimientos: {}", requirementsStr, e);
      return requirementsStr; // Retornar original si hay error
    }
  }

  private Optional<String> fetchRequirementName(Integer requirementId) {
    try {
      log.debug("Consultando requerimiento con ID: {}", requirementId);

      return requerimentRepository.findById(requirementId)
          .map(requirement -> {
            String name = requirement.getName();
            log.info("Requerimiento encontrado: {}", name);
            return name;
          });

    } catch (Exception e) {
      log.error("Error al consultar requerimiento con ID {}: {}", requirementId, e.getMessage());
      return Optional.empty();
    }
  }

  private void enrichServiceStatusChange(Map<String, Object> change) {
    Object initialValue = change.get("initialValue");
    Object lastValue = change.get("lastValue");

    log.debug("Enriqueciendo cambio de estado. Initial: {}, Last: {}", initialValue, lastValue);

    if (isValidInteger(initialValue)) {
      try {
        Integer statusId = Integer.parseInt(initialValue.toString());
        fetchServiceStatusName(statusId).ifPresent(name -> {
          change.put("initialValue", name);
          log.info("✅ Estado anterior enriquecido: {}", name);
        });
      } catch (NumberFormatException e) {
        log.warn("ID de estado inválido: {}", initialValue);
      }
    }

    // Enriquecer estado nuevo
    if (isValidInteger(lastValue)) {
      try {
        Integer statusId = Integer.parseInt(lastValue.toString());
        fetchServiceStatusName(statusId).ifPresent(name -> {
          change.put("lastValue", name);
          log.info("✅ Estado nuevo enriquecido: {}", name);
        });
      } catch (NumberFormatException e) {
        log.warn("ID de estado inválido: {}", lastValue);
      }
    }
  }

  /**
   * ✅ NUEVO: Obtener nombre del estado desde BD local
   */
  private Optional<String> fetchServiceStatusName(Integer statusId) {
    try {
      log.debug("Consultando estado de servicio en BD local con ID: {}", statusId);

      return serviceStatusRepository.findById(statusId)
          .map(status -> {
            String name = status.getName(); // O status.getDescription() según tu entidad
            log.info("Estado encontrado: {}", name);
            return name;
          });

    } catch (Exception e) {
      log.error("Error al consultar estado con ID {}: {}", statusId, e.getMessage());
      return Optional.empty();
    }
  }
}