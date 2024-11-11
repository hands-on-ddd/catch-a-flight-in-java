// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// -------------------------------------------------------------------------------------------------------------------------------------------------------------
package io.hands.on.ddd.account.model;

import io.hands.on.ddd.common.annotation.domain.DomainValueObject;
import java.util.Objects;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * User's name value object.
 * @param firstName user's first name
 * @param lastName  user's last name
 */
@DomainValueObject
public record UserName(String firstName, String lastName) {
  public UserName {
    Objects.requireNonNull(firstName);
    Objects.requireNonNull(lastName);
  }
}
