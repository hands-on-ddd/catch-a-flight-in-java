// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

package io.hands.on.ddd.account.infrastructure;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import static io.hands.on.ddd.account.AccountUserConfiguration.MODULE_PREFIX;
import static io.hands.on.ddd.account.infrastructure.InfrastructureConfiguration.BeanNames.*;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Spring configuration for infrastructure layer in the module.
 */
@EntityScan
@EnableJdbcRepositories
@Configuration(value = INFRASTRUCTURE_CONFIGURATION, proxyBeanMethods = false)
public class InfrastructureConfiguration {
    @Bean(ACCOUNT_JDBC_ENTITY_MAPPER)
    AccountJdbcEntityMapper userJdbcEntityMapper() {
        return new AccountJdbcEntityMapper();
    }

    @Bean(ACCOUNT_REPOSITORY)
    AccountRepository userRepository(
          @Qualifier(ACCOUNT_JDBC_REPOSITORY) AccountJdbcRepository repository,
          @Qualifier(ACCOUNT_JDBC_ENTITY_MAPPER) AccountJdbcEntityMapper mapper) {
        return new AccountRepository(repository, mapper);
    }

    public static class BeanNames {
        public static final String INFRASTRUCTURE_CONFIGURATION = MODULE_PREFIX + "InfrastructureConfiguration";
        public static final String ACCOUNT_JDBC_REPOSITORY = MODULE_PREFIX + "AccountJdbcRepository";
        public static final String ACCOUNT_REPOSITORY = MODULE_PREFIX + "AccountRepository";
        public static final String ACCOUNT_JDBC_ENTITY_MAPPER = MODULE_PREFIX + "AccountJdbcEntityMapper";
    }
}
