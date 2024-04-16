// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

package io.hands.on.ddd.account.model;

import io.hands.on.ddd.common.annotation.domain.DomainRepository;
import io.hands.on.ddd.common.annotation.hexagonal.OutboundPort;
import io.hands.on.hands.sharedkernel.UserId;

import java.util.Optional;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Functional interface for loading Account from persistence layer.
 */
@OutboundPort
@DomainRepository
@FunctionalInterface
public interface FindAccountRepository {
    Optional<Account> load(UserId userId);
}
