package com.ectransport.platform.domain.application.security;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class JwtTokenInterceptor implements ClientHttpRequestInterceptor {

  private String token;

  public void setToken(String token) {
    this.token = token;
  }

  @Override
  public ClientHttpResponse intercept(
      HttpRequest request,
      byte[] body,
      ClientHttpRequestExecution execution) throws IOException {

    if (token != null && !token.isEmpty()) {
      request.getHeaders().set("Authorization", "Bearer " + token);
    }

    return execution.execute(request, body);
  }
}