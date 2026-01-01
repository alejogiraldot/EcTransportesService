package com.ectransport.platform.domain.application.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

@Component
@Slf4j
public class JwtTokenProvider {
  private static final String AUTHORIZATION_HEADER = "Authorization";
  private static final String BEARER_PREFIX = "Bearer ";
  private static final int BEARER_PREFIX_LENGTH = 7;


  public Optional<String> getCurrentToken() {
    return getAuthorizationHeader()
        .filter(this::isBearerToken)
        .map(this::extractToken);
  }


  private Optional<String> getAuthorizationHeader() {
    try {
      return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
          .filter(ServletRequestAttributes.class::isInstance)
          .map(ServletRequestAttributes.class::cast)
          .map(ServletRequestAttributes::getRequest)
          .map(request -> request.getHeader(AUTHORIZATION_HEADER));
    } catch (Exception e) {
      log.warn("Error obteniendo el header Authorization: {}", e.getMessage());
      return Optional.empty();
    }
  }

  private boolean isBearerToken(String authHeader) {
    return authHeader != null && authHeader.startsWith(BEARER_PREFIX);
  }

  private String extractToken(String authHeader) {
    return authHeader.substring(BEARER_PREFIX_LENGTH);
  }
}
