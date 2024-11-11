// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// -------------------------------------------------------------------------------------------------------------------------------------------------------------
package io.hands.on.ddd.account.domain;

import io.hands.on.ddd.common.annotation.domain.DomainRepository;
import io.hands.on.ddd.common.annotation.hexagonal.OutboundPort;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Functional interface for persisting User aggregate.
 */
@OutboundPort
@DomainRepository
@FunctionalInterface
public interface CreateAccountRepository {
  Account create(Account account);
}
