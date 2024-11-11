// ---------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// ---------------------------------------------------------------------------------------------------------------------
package io.hands.on.ddd.account.domain;

import io.hands.on.ddd.common.annotation.domain.DomainValueObject;
import java.util.Objects;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Unencrypted password sent by user.
 *
 * @param value plain text password passed from user interface to use case
 */
@DomainValueObject
public record Password(String value) {
  public Password {
    Objects.requireNonNull(value);
  }
}
