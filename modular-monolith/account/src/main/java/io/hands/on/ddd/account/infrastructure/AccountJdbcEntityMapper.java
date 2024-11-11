// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// -------------------------------------------------------------------------------------------------------------------------------------------------------------
package io.hands.on.ddd.account.infrastructure;

import io.hands.on.ddd.account.domain.Account;
import io.hands.on.ddd.account.domain.Password;
import io.hands.on.ddd.account.domain.UserName;
import io.hands.on.hands.sharedkernel.Email;
import io.hands.on.hands.sharedkernel.UserId;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Maps account domain model to/from entity.
 */
class AccountJdbcEntityMapper {
  AccountJdbcEntity toJdbcEntity(Account account) {
    var userId = account.getUserId();
    var userName = account.getUserName();
    var email = account.getEmail();
    var password = account.getPassword();
    var userType = account.getAccountType();

    return new AccountJdbcEntity(
        userId != null ? userId.value() : null,
        email.value(),
        password.value(),
        userName.firstName(),
        userName.lastName(),
        userType);
  }

  Account toDomain(AccountJdbcEntity entity) {
    var userId = new UserId(entity.id());
    var userName = new UserName(entity.firstName(), entity.lastName());
    var email = new Email(entity.email());
    var password = new Password(entity.password());
    var userType = entity.accountType();

    return Account.builder()
        .userId(userId)
        .userName(userName)
        .email(email)
        .password(password)
        .accountType(userType)
        .build();
  }
}
