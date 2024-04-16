package io.hands.on.ddd.account.model;

import io.hands.on.ddd.common.annotation.domain.DomainRepository;
import io.hands.on.ddd.common.annotation.hexagonal.OutboundPort;

/**
 * Functional interface for persisting User aggregate.
 */
@OutboundPort
@DomainRepository
@FunctionalInterface
public interface CreateAccountRepository {
    Account create(Account account);
}
