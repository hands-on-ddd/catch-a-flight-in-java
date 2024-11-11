// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// -------------------------------------------------------------------------------------------------------------------------------------------------------------
package io.hands.on.ddd.account.application;

import static io.hands.on.ddd.account.AccountUserConfiguration.MODULE_PREFIX;
import static io.hands.on.ddd.account.application.ApplicationConfiguration.BeanNames.*;
import static io.hands.on.ddd.account.infrastructure.InfrastructureConfiguration.BeanNames.ACCOUNT_REPOSITORY;

import io.hands.on.ddd.account.domain.*;
import io.hands.on.ddd.common.event.DomainEventsPublisher;
import io.hands.on.ddd.common.event.JustForwardDomainEventPublisher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Spring configuration for application layer in the module.
 */
@Configuration(value = APPLICATION_CONFIGURATION, proxyBeanMethods = false)
public class ApplicationConfiguration {
  @Bean(DOMAIN_EVENTS_PUBLISHER)
  DomainEventsPublisher domainEventsPublisher(ApplicationEventPublisher eventPublisher) {
    return new JustForwardDomainEventPublisher(eventPublisher);
  }

  @Bean(USER_FACTORY)
  AccountFactory userFactory(
      @Qualifier(ACCOUNT_REPOSITORY) FindCurrentAccountRepository repository) {
    return new AccountFactory(repository);
  }

  @Bean(CREATE_USER_USECASE)
  CreateAccountUseCase createUserUseCase(
      @Qualifier(USER_FACTORY) AccountFactory factory,
      @Qualifier(ACCOUNT_REPOSITORY) CreateAccountRepository repository,
      @Qualifier(DOMAIN_EVENTS_PUBLISHER) DomainEventsPublisher publisher) {
    return new CreateAccountUseCase(factory, repository, publisher);
  }

  @Bean(UPGRADE_USER_USECASE)
  UpgradeAccountUseCase upgradeUserUseCase(
      @Qualifier(ACCOUNT_REPOSITORY) FindAccountRepository findAccountRepository,
      @Qualifier(ACCOUNT_REPOSITORY) UpdateAccountRepository updateAccountRepository,
      @Qualifier(DOMAIN_EVENTS_PUBLISHER) DomainEventsPublisher publisher) {
    return new UpgradeAccountUseCase(findAccountRepository, updateAccountRepository, publisher);
  }

  @Bean(SIGN_IN_USECASE)
  SignInUseCase signInUseCase(@Qualifier(DOMAIN_EVENTS_PUBLISHER) DomainEventsPublisher publisher) {
    return new SignInUseCase(publisher);
  }

  static class BeanNames {
    public static final String APPLICATION_CONFIGURATION =
        MODULE_PREFIX + "ApplicationConfiguration";
    public static final String DOMAIN_EVENTS_PUBLISHER = MODULE_PREFIX + "DomainEventsPublisher";
    public static final String SIGN_IN_USECASE = MODULE_PREFIX + "SignInUserUseCase";
    public static final String USER_FACTORY = MODULE_PREFIX + "UserFactory";
    public static final String CREATE_USER_USECASE = MODULE_PREFIX + "CreateUserUseCase";
    public static final String UPGRADE_USER_USECASE = MODULE_PREFIX + "UpgradeUserUseCase";
  }
}
