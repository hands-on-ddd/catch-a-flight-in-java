// ---------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// ---------------------------------------------------------------------------------------------------------------------
package io.hands.on.ddd.bestdeal.application;

import static io.hands.on.ddd.bestdeal.BestDealsFlightConfiguration.MODULE_PREFIX;
import static io.hands.on.ddd.bestdeal.application.ApplicationConfiguration.BeanNames.*;
import static io.hands.on.ddd.bestdeal.application.ApplicationConfiguration.BeanNames.APPLICATION_CONFIGURATION;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/** Spring configuration for application layer in the module. */
@Configuration(value = APPLICATION_CONFIGURATION, proxyBeanMethods = false)
public class ApplicationConfiguration {
  @Bean(BeanNames.INDEX_BESTDEALS_USECASE)
  IndexBestDealsUseCase indexBestDealsUseCase() {
    return new IndexBestDealsUseCase();
  }

  @Bean(BeanNames.INDEX_FLIGHT_QUERY_USECASE)
  IndexFlightQueryUseCase indexFlightQueryUseCase() {
    return new IndexFlightQueryUseCase();
  }

  public static class BeanNames {
    public static final String APPLICATION_CONFIGURATION =
        MODULE_PREFIX + "ApplicationConfiguration";
    public static final String INDEX_BESTDEALS_USECASE = MODULE_PREFIX + "IndexBestDealsUseCase";
    public static final String INDEX_FLIGHT_QUERY_USECASE =
        MODULE_PREFIX + "IndexFlightQueryUseCase";
  }
}
