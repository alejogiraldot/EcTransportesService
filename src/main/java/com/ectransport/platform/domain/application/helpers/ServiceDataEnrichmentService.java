package com.ectransport.platform.domain.application.helpers;

import com.ectransport.platform.domain.application.dto.ClientDto;
import com.ectransport.platform.domain.application.dto.RequestCreateServiceDto;
import com.ectransport.platform.domain.application.dto.UserDto;
import com.ectransport.platform.domain.application.security.JwtTokenInterceptor;
import com.ectransport.platform.domain.application.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;


@Service
@Slf4j
public class ServiceDataEnrichmentService {

  private final RestTemplate restTemplate;
  private final JwtTokenInterceptor jwtTokenInterceptor;
  private final JwtTokenProvider tokenProvider;

  @Value("${get.driver.url}")
  private String driverServiceUrl;

  @Value("${get.client.url}")
  private String clientServiceUrl;

  public ServiceDataEnrichmentService(
      RestTemplate restTemplate,
      JwtTokenInterceptor jwtTokenInterceptor,
      JwtTokenProvider tokenProvider) {
    this.restTemplate = restTemplate;
    this.jwtTokenInterceptor = jwtTokenInterceptor;
    this.tokenProvider = tokenProvider;
  }


  public void enrichServiceData(RequestCreateServiceDto dto) {
    executeWithToken(() -> {
      enrichDriverData(dto);
      enrichClientData(dto);
    });
  }


  private void executeWithToken(Runnable operation) {
    Optional<String> token = tokenProvider.getCurrentToken();

    if (token.isEmpty()) {
      log.warn("No se pudo obtener el token JWT. No se enriquecerán los datos.");
      return;
    }

    jwtTokenInterceptor.setToken(token.get());
    try {
      operation.run();
    } finally {
      jwtTokenInterceptor.setToken(null);
    }
  }


  private void enrichDriverData(RequestCreateServiceDto dto) {
    if (dto.getFkDriver() == null) {
      return;
    }

    fetchDriverName(dto.getFkDriver())
        .ifPresent(dto::setDriverName);
  }


  private void enrichClientData(RequestCreateServiceDto dto) {
    if (dto.getFkClient() == null) {
      return;
    }

    fetchClientName(dto.getFkClient())
        .ifPresent(dto::setClientName);
  }


  private Optional<String> fetchDriverName(Integer driverId) {
    log.debug("Obteniendo información del conductor ID: {}", driverId);

    return fetchFromService(
        driverServiceUrl + driverId,
        new ParameterizedTypeReference<UserDto>() {
        },
        "conductor",
        driverId
    ).map(driver -> driver.getName() + " " + driver.getLastName());
  }


  private Optional<String> fetchClientName(UUID clientId) {
    log.debug("Obteniendo información del cliente ID: {}", clientId);

    return fetchFromService(
        clientServiceUrl + clientId,
        new ParameterizedTypeReference<ClientDto>() {
        },
        "cliente",
        clientId
    ).map(ClientDto::getTradeName);
  }


  private <T> Optional<T> fetchFromService(
      String url,
      ParameterizedTypeReference<T> responseType,
      String entityName,
      Object entityId) {

    try {
      log.debug("Consultando {} en: {}", entityName, url);

      ResponseEntity<T> response = restTemplate.exchange(
          url,
          HttpMethod.GET,
          null,
          responseType
      );

      if (response.getBody() != null) {
        log.info("{} encontrado con ID: {}", capitalize(entityName), entityId);
        return Optional.of(response.getBody());
      }

      return Optional.empty();

    } catch (RestClientException e) {
      log.error("Error al consultar {} con ID {}: {}", entityName, entityId, e.getMessage());
      return Optional.empty();
    }
  }


  private String capitalize(String str) {
    if (str == null || str.isEmpty()) {
      return str;
    }
    return str.substring(0, 1).toUpperCase() + str.substring(1);
  }
}