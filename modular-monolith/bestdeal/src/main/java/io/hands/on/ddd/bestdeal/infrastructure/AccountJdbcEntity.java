// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// -------------------------------------------------------------------------------------------------------------------------------------------------------------
package io.hands.on.ddd.bestdeal.infrastructure;

import io.hands.on.hands.sharedkernel.AccountType;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

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
 */
@Table(name = "ACCOUNTS")
public record AccountJdbcEntity(
    @Id UUID id,
    String email,
    String password,
    String firstName,
    String lastName,
    AccountType accountType) {}
