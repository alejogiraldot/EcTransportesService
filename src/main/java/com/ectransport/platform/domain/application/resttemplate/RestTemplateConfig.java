package com.ectransport.platform.domain.application.resttemplate;

import com.ectransport.platform.domain.application.security.JwtTokenInterceptor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class RestTemplateConfig {

  @Bean
  public JwtTokenInterceptor jwtTokenInterceptor() {
    return new JwtTokenInterceptor();
  }

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder, JwtTokenInterceptor interceptor) {
    return builder
        .requestFactory(() -> {
          SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
          factory.setConnectTimeout(5000);
          factory.setReadTimeout(5000);
          return factory;
        })
        .interceptors(Collections.singletonList(interceptor))
        .build();
  }
}