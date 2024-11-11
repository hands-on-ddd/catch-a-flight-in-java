// ---------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// ---------------------------------------------------------------------------------------------------------------------
package io.hands.on.ddd.account.domain;

import io.hands.on.hands.sharedkernel.UserId;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/** Thrown when user is already of Premium type, so it cannot be upgraded. */
public class AccountAlreadyUpgradedException extends RuntimeException {
  private final UserId userId;

  public AccountAlreadyUpgradedException(UserId userId, String message) {
    super(message);
    this.userId = userId;
  }

  @Override
  public String getMessage() {
    return super.getMessage() + "[" + userId.value() + "]";
  }
}
