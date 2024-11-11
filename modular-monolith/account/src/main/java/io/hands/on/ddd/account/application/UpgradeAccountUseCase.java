// ---------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// ---------------------------------------------------------------------------------------------------------------------
package io.hands.on.ddd.account.application;

import io.hands.on.ddd.account.domain.AccountAlreadyUpgradedException;
import io.hands.on.ddd.account.domain.FindAccountRepository;
import io.hands.on.ddd.account.domain.UpdateAccountRepository;
import io.hands.on.ddd.account.domain.event.AccountUpgradeFailed;
import io.hands.on.ddd.account.domain.event.AccountUpgraded;
import io.hands.on.ddd.common.annotation.hexagonal.InboundPort;
import io.hands.on.ddd.common.annotation.hexagonal.UseCase;
import io.hands.on.ddd.common.event.DomainEventsPublisher;
import io.hands.on.hands.sharedkernel.UserId;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

// ---------------------------------------------------------------------------------------------------------------------
// Implementation
// ---------------------------------------------------------------------------------------------------------------------

/** Use case for upgrading user (from Regular to Premium). */
@Slf4j
@UseCase
@InboundPort
@RequiredArgsConstructor
public class UpgradeAccountUseCase {
  private final FindAccountRepository findAccountRepository;
  private final UpdateAccountRepository updateAccountRepository;
  private final DomainEventsPublisher domainEventsPublisher;

  @Transactional
  public UpgradeUserResult upgradeUser(UpgradeUserCommand command) {
    try {
      var userOptional = findAccountRepository.load(command.userId());
      if (userOptional.isPresent()) {
        var user = userOptional.get();
        user.upgradeUser();
        updateAccountRepository.save(user);
        emitUserUpgraded(command.userId());

        return new UpgradeUserResult.Success();
      } else {
        return new UpgradeUserResult.UserNotFoundFailure("User not found");
      }
    } catch (AccountAlreadyUpgradedException ex) {
      emitUserCreationFailed(command.userId, ex.getMessage());
      return new UpgradeUserResult.UserAlreadyUpgradedFailure(ex.getMessage());

    } catch (Exception ex) {
      emitUserCreationFailed(command.userId(), ex.getMessage());
      return new UpgradeUserResult.InternalFailure(ex);
    }
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Private Section
  // -------------------------------------------------------------------------------------------------------------------

  private void emitUserUpgraded(UserId userId) {
    domainEventsPublisher.publish(userUpgradedEvent(userId));
  }

  private void emitUserCreationFailed(UserId userId, String message) {
    domainEventsPublisher.publish(userUpgradedFailedEvent(userId, message));
  }

  private AccountUpgraded userUpgradedEvent(UserId userId) {
    return new AccountUpgraded(UUID.randomUUID(), userId);
  }

  private AccountUpgradeFailed userUpgradedFailedEvent(UserId userId, String message) {
    return new AccountUpgradeFailed(UUID.randomUUID(), userId, message);
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Static Types Section
  // -------------------------------------------------------------------------------------------------------------------

  public record UpgradeUserCommand(UserId userId) {
    public UpgradeUserCommand {
      Objects.requireNonNull(userId);
    }
  }

  /** Upgrade user operation result */
  public sealed interface UpgradeUserResult {
    /** Success response */
    record Success() implements UpgradeUserResult {}

    /** User was not found in the system */
    record UserNotFoundFailure(String message) implements UpgradeUserResult {}

    /** User is already upgraded to premium */
    record UserAlreadyUpgradedFailure(String message) implements UpgradeUserResult {}

    /** Unexpected internal failture */
    record InternalFailure(Throwable cause) implements UpgradeUserResult {}
  }
}
