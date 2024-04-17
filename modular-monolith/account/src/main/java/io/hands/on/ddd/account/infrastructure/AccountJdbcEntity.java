// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

package io.hands.on.ddd.account.infrastructure;

import io.hands.on.hands.sharedkernel.AccountType;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Account entity (Spring Data JDBC).
 * @param id          primary key
 * @param email       email provided at registration
 * @param password    encrypted password
 * @param firstName   user's first name
 * @param lastName    user's last name
 * @param accountType type of the user (Regular or Premium, at the moment)
 * @param version     version for handling optimistic locking
 */
@Table(name = "ACCOUNTS")
public record AccountJdbcEntity(
      @Id
      UUID id,
      String email,
      String password,
      String firstName,
      String lastName,
      AccountType accountType) {
}
