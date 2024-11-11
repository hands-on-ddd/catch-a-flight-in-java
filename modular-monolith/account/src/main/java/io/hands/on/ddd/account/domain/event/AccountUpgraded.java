// ---------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// ---------------------------------------------------------------------------------------------------------------------
package io.hands.on.ddd.account.domain.event;

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
 *
 * @param eventId event identifier
 * @param userId user identifier
 */
@Event
public record AccountUpgraded(UUID eventId, UserId userId) implements DomainEvent {
  public AccountUpgraded {
    Objects.requireNonNull(eventId);
    Objects.requireNonNull(userId);
  }
}
