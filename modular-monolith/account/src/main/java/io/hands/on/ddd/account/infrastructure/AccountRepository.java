// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// -------------------------------------------------------------------------------------------------------------------------------------------------------------
package io.hands.on.ddd.account.infrastructure;

import io.hands.on.ddd.account.model.*;
import io.hands.on.hands.sharedkernel.*;
import io.pmarat.catchflight.common.annotations.hexagonal.PersistenceAdapter;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Domain repository for persistence on Account aggregate.
 */
@PersistenceAdapter
@RequiredArgsConstructor
class AccountRepository
    implements CreateAccountRepository,
        FindCurrentAccountRepository,
        FindAccountRepository,
        UpdateAccountRepository {
  private final AccountJdbcRepository accountJdbcRepository;
  private final AccountJdbcEntityMapper accountJdbcEntityMapper;

  @Override
  public Optional<Account> load(UserId userId) {
    return accountJdbcRepository.findById(userId.value()).map(accountJdbcEntityMapper::toDomain);
  }

  @Override
  public CurrentAccount findByEmail(Email email) {
    return accountJdbcRepository
        .findByEmail(email.value())
        .map(this::existingUser)
        .orElseGet(this::nonExistingUser);
  }

  @Override
  public Account create(Account account) {
    var accountJdbcEntity = accountJdbcEntityMapper.toJdbcEntity(account);
    var createdJdbcEntity = accountJdbcRepository.save(accountJdbcEntity);
    return accountJdbcEntityMapper.toDomain(createdJdbcEntity);
  }

  @Override
  public void save(Account account) {
    accountJdbcRepository.save(accountJdbcEntityMapper.toJdbcEntity(account));
  }

  private CurrentAccount existingUser(AccountJdbcEntity accountJdbcEntity) {
    return switch (accountJdbcEntity.accountType()) {
      case REGULAR -> new RegularAccount(new UserId(accountJdbcEntity.id()));
      case PREMIUM -> new PremiumAccount(new UserId(accountJdbcEntity.id()));
    };
  }

  private NonExistingAccount nonExistingUser() {
    return new NonExistingAccount();
  }
}
