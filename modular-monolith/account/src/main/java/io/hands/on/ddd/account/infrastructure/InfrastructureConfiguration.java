// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

package io.hands.on.ddd.account.infrastructure;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.hands.on.ddd.account.AccountUserConfiguration.MODULE_PREFIX;
import static io.hands.on.ddd.account.infrastructure.InfrastructureConfiguration.BeanNames.*;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Spring configuration for infrastructure layer in the module.
 */
@Configuration(value = INFRASTRUCTURE_CONFIGURATION, proxyBeanMethods = false)
public class InfrastructureConfiguration {
    public static class BeanNames {
        public static final String INFRASTRUCTURE_CONFIGURATION = MODULE_PREFIX + "InfrastructureConfiguration";
        public static final String ACCOUNT_REPOSITORY = MODULE_PREFIX + "AccountRepository";
    }
}
