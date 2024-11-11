// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// -------------------------------------------------------------------------------------------------------------------------------------------------------------
package io.hands.on.ddd.account.domain;

import static io.hands.on.ddd.common.policy.DomainPolicyOutput.*;

import io.hands.on.ddd.common.annotation.domain.DomainFactory;
import io.hands.on.hands.sharedkernel.*;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Account aggregate factory.
 */
@Slf4j
@DomainFactory
@RequiredArgsConstructor
public class AccountFactory {
  private final FindCurrentAccountRepository findCurrentAccountRepository;
  private final List<PasswordPolicy> passwordPolicies = PasswordPolicy.passwordPolicies;

  public Account create(Email email, Password password, UserName userName) {
    verifyPasswordPolicies(password);
    verifyIfUserAlreadyExists(email);
    return buildUser(email, password, userName);
  }

  // ---------------------------------------------------------------------------------------------------------------------------------------------------------
  // Private Section
  // ---------------------------------------------------------------------------------------------------------------------------------------------------------

  private void verifyPasswordPolicies(Password password) {
    var policyOutput =
        passwordPolicies.stream()
            .map(policy -> policy.apply(password))
            .filter(domainPolicyOutput -> domainPolicyOutput instanceof Rejection)
            .findFirst()
            .orElse(new Allowance());

    if (policyOutput instanceof Rejection(String reason)) {
      throw new PasswordPolicyException(reason);
    }
  }

  private void verifyIfUserAlreadyExists(Email email) {
    var currentUser = findCurrentAccountRepository.findByEmail(email);
    switch (currentUser) {
      case RegularAccount(UserId userId) -> throw new AccountAlreadyExistsException(userId);
      case PremiumAccount(UserId userId) -> throw new AccountAlreadyExistsException(userId);
      case NonExistingAccount _ -> log.debug("All good. User does not exist yet.");
    }
  }

  private Account buildUser(Email email, Password password, UserName userName) {
    return Account.builder()
        .email(email)
        .password(password)
        .userName(userName)
        .accountType(AccountType.REGULAR)
        .build();
  }
}
