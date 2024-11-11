// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// -------------------------------------------------------------------------------------------------------------------------------------------------------------
package io.hands.on.ddd.account.model;

import io.hands.on.ddd.common.annotation.domain.DomainAggregate;
import io.hands.on.hands.sharedkernel.AccountType;
import io.hands.on.hands.sharedkernel.Email;
import io.hands.on.hands.sharedkernel.UserId;
import lombok.Builder;
import lombok.Getter;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Account aggregate. Guards all account's invariants.
 */
@Getter
@Builder
@DomainAggregate
public class Account {
  UserId userId;
  Email email;
  Password password;
  UserName userName;
  AccountType accountType;

  public void upgradeUser() {
    switch (accountType) {
      case PREMIUM ->
          throw new AccountAlreadyUpgradedException(userId, "Premium user can't be upgraded");
      case REGULAR -> accountType = AccountType.PREMIUM;
    }
  }
}
