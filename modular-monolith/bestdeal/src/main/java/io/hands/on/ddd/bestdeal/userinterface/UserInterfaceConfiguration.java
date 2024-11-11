// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// -------------------------------------------------------------------------------------------------------------------------------------------------------------
package io.hands.on.ddd.bestdeal.userinterface;

import static io.hands.on.ddd.bestdeal.BestDealsFlightConfiguration.MODULE_PREFIX;
import static io.hands.on.ddd.bestdeal.application.ApplicationConfiguration.BeanNames.INDEX_BESTDEALS_USECASE;
import static io.hands.on.ddd.bestdeal.application.ApplicationConfiguration.BeanNames.INDEX_FLIGHT_QUERY_USECASE;
import static io.hands.on.ddd.bestdeal.userinterface.UserInterfaceConfiguration.BeanNames.*;

import io.hands.on.ddd.bestdeal.application.IndexBestDealsUseCase;
import io.hands.on.ddd.bestdeal.application.IndexFlightQueryUseCase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Spring configuration for user interface layer in the module.
 */
@Configuration(value = USER_INTERFACE_CONFIGURATION, proxyBeanMethods = false)
public class UserInterfaceConfiguration {
  @Bean(FLIGHT_QUERY_HANDLER)
  FlightOfferCollectedHandler flightQueryPerformedHandler(
      @Qualifier(INDEX_FLIGHT_QUERY_USECASE) IndexFlightQueryUseCase useCase) {
    return new FlightOfferCollectedHandler(useCase);
  }

  @Bean(INDEX_BESTDEALS_SCHEDULER)
  IndexBestDealsScheduler indexBestDealsScheduler(
      @Qualifier(INDEX_BESTDEALS_USECASE) IndexBestDealsUseCase useCase) {
    return new IndexBestDealsScheduler(useCase);
  }

  public static class BeanNames {
    public static final String USER_INTERFACE_CONFIGURATION =
        MODULE_PREFIX + "UserInterfaceConfiguration";
    public static final String FLIGHT_QUERY_HANDLER = MODULE_PREFIX + "FlightQueryPerformedHandler";
    public static final String INDEX_BESTDEALS_SCHEDULER =
        MODULE_PREFIX + "IndexBestDealsScheduler";
  }
}
