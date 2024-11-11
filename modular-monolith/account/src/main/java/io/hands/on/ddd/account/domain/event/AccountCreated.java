// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// -------------------------------------------------------------------------------------------------------------------------------------------------------------
package io.hands.on.ddd.account.domain.event;

import io.hands.on.ddd.account.domain.UserName;
import io.hands.on.ddd.common.annotation.event.Event;
import io.hands.on.ddd.common.event.DomainEvent;
import io.hands.on.hands.sharedkernel.AccountType;
import io.hands.on.hands.sharedkernel.Email;
import io.hands.on.hands.sharedkernel.UserId;
import java.util.Objects;
import java.util.UUID;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Account created event.
 * @param eventId     event identifier
 * @param userId      user identifier
 * @param userName    user name
 * @param accountType user type
 * @param email       user email
 */
@Event
public record AccountCreated(
    UUID eventId, UserId userId, UserName userName, AccountType accountType, Email email)
    implements DomainEvent {
  public AccountCreated {
    Objects.requireNonNull(eventId);
    Objects.requireNonNull(userId);
    Objects.requireNonNull(userName);
    Objects.requireNonNull(accountType);
    Objects.requireNonNull(email);
  }
}
