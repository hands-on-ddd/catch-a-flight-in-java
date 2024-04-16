// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

package io.hands.on.ddd.account;

import io.hands.on.ddd.account.application.ApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Main spring configuration aggregator for the module.
 */
@Configuration(proxyBeanMethods = false)
@ComponentScan(AccountUserConfiguration.PACKAGE_TO_SCAN)
@Import({
      ApplicationConfiguration.class,
})
public class AccountUserConfiguration {
    public static final String MODULE_PREFIX = "account";
    public static final String PACKAGE_TO_SCAN = "io.pmarat.catchflight.account";
}
