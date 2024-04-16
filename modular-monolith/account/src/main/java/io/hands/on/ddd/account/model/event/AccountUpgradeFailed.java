// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

package io.hands.on.ddd.account.model.event;

import io.hands.on.ddd.common.annotation.event.Event;
import io.hands.on.ddd.common.event.DomainEvent;
import io.hands.on.hands.sharedkernel.UserId;

import java.util.Objects;
import java.util.UUID;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Account deleted event.
 * @param eventId event identifier
 * @param userId  user identifier
 * @param message reason why operation failed
 */
@Event
public record AccountUpgradeFailed(UUID eventId, UserId userId, String message) implements DomainEvent {
    public AccountUpgradeFailed {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(userId);
        Objects.requireNonNull(message);
    }
}
