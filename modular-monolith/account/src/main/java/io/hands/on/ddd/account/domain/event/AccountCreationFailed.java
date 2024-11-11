// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// -------------------------------------------------------------------------------------------------------------------------------------------------------------
package io.hands.on.ddd.account.domain.event;

import io.hands.on.ddd.account.domain.UserName;
import io.hands.on.ddd.common.annotation.event.Event;
import io.hands.on.ddd.common.event.DomainEvent;
import io.hands.on.hands.sharedkernel.Email;
import java.util.Objects;
import java.util.UUID;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Account creation failed event.
 * @param eventId  event identifier
 * @param userName user full name
 * @param email    user email
 * @param message  reason why operation failed
 */
@Event
public record AccountCreationFailed(UUID eventId, UserName userName, Email email, String message)
    implements DomainEvent {
  public AccountCreationFailed {
    Objects.requireNonNull(eventId);
    Objects.requireNonNull(userName);
    Objects.requireNonNull(email);
    Objects.requireNonNull(message);
  }
}
