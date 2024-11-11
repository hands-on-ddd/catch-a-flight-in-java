// ---------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// ---------------------------------------------------------------------------------------------------------------------
package io.hands.on.ddd.account.userinterface;

import static io.hands.on.ddd.account.AccountUserConfiguration.MODULE_PREFIX;

import io.hands.on.ddd.account.application.UpgradeAccountUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/** Spring configuration for user interface layer in the module. */
@Configuration(
    value = UserInterfaceConfiguration.BeanNames.USER_INTERFACE_CONFIGURATION,
    proxyBeanMethods = false)
public class UserInterfaceConfiguration {
  @Bean(BeanNames.SUBSCRIPTION_PAID_HANDLER)
  SubscriptionPaidEventHandler subscriptionPaidEventHandler(UpgradeAccountUseCase useCase) {
    return new SubscriptionPaidEventHandler(useCase);
  }

  static class BeanNames {
    public static final String USER_INTERFACE_CONFIGURATION =
        MODULE_PREFIX + "UserInterfaceConfiguration";
    public static final String SUBSCRIPTION_PAID_HANDLER =
        MODULE_PREFIX + "SubscriptionPaidEventHandler";
  }
}
