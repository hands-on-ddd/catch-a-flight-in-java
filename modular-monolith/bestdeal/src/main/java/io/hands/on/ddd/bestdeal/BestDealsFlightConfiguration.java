// ---------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// ---------------------------------------------------------------------------------------------------------------------
package io.hands.on.ddd.bestdeal;

import io.hands.on.ddd.bestdeal.application.ApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/** Main spring configuration aggregator for the module. */
@Configuration(proxyBeanMethods = false)
@ComponentScan(BestDealsFlightConfiguration.PACKAGE_TO_SCAN)
@Import({
  ApplicationConfiguration.class,
})
public class BestDealsFlightConfiguration {
  public static final String MODULE_PREFIX = "bestDeal";
  public static final String PACKAGE_TO_SCAN = "io.pmarat.catchflight.bestdeals";
}
