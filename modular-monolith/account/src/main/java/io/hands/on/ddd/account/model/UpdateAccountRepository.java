// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

package io.hands.on.ddd.account.model;

import io.hands.on.ddd.common.annotation.domain.DomainRepository;
import io.hands.on.ddd.common.annotation.hexagonal.OutboundPort;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Functional interface for updating Account in persistence layer.
 */
@OutboundPort
@DomainRepository
@FunctionalInterface
public interface UpdateAccountRepository {
    void save(Account account);
}
