// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

package io.hands.on.ddd.account.application;

import io.hands.on.ddd.account.model.*;
import io.hands.on.ddd.account.model.event.AccountCreated;
import io.hands.on.ddd.account.model.event.AccountCreationFailed;
import io.hands.on.ddd.common.annotation.hexagonal.InboundPort;
import io.hands.on.ddd.common.annotation.hexagonal.UseCase;
import io.hands.on.ddd.common.event.DomainEventsPublisher;
import io.hands.on.hands.sharedkernel.AccountAlreadyExistsException;
import io.hands.on.hands.sharedkernel.Email;
import io.hands.on.hands.sharedkernel.UserId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Use case for creating user.
 */
@Slf4j
@UseCase
@InboundPort
@RequiredArgsConstructor
public class CreateAccountUseCase {
    private final AccountFactory accountFactory;
    private final CreateAccountRepository createAccountRepository;
    private final DomainEventsPublisher domainEventsPublisher;

    @Transactional
    public CreateAccountResult createUser(CreateAccountCommand command) {
        try {
            var user = accountFactory.create(command.email(), command.password(), command.userName());
            var persistedUser = createAccountRepository.create(user);
            emitAccountCreated(persistedUser);
            return new CreateAccountResult.Success(persistedUser.getUserId());

        } catch (AccountAlreadyExistsException ex) {
            emitAccountCreationFailed(command, ex.getMessage());
            return new CreateAccountResult.ExistingAccountFailure(ex.getMessage());

        } catch (PasswordPolicyException ex) {
            emitAccountCreationFailed(command, ex.getMessage());
            return new CreateAccountResult.PasswordPolicyFailure(ex.getMessage());

        } catch (Exception ex) {
            emitAccountCreationFailed(command, ex.getMessage());
            return new CreateAccountResult.InternalFailure(ex);
        }
    }

    // ---------------------------------------------------------------------------------------------------------------------------------------------------------
    // Private Section
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------

    private void emitAccountCreated(Account account) {
        domainEventsPublisher.publish(accountCreatedEvent(account));
    }

    private void emitAccountCreationFailed(CreateAccountCommand command, String message) {
        domainEventsPublisher.publish(accountCreationFailedEvent(command, message));
    }

    private AccountCreated accountCreatedEvent(Account account) {
        return new AccountCreated(UUID.randomUUID(),
              account.getUserId(),
              account.getUserName(),
              account.getAccountType(),
              account.getEmail()
        );
    }

    private AccountCreationFailed accountCreationFailedEvent(CreateAccountCommand command, String message) {
        return new AccountCreationFailed(
              UUID.randomUUID(),
              command.userName(),
              command.email(),
              message);
    }

    // ---------------------------------------------------------------------------------------------------------------------------------------------------------
    // Static Types Section
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------

    public record CreateAccountCommand(Email email, Password password, UserName userName) {
        public CreateAccountCommand {
            Objects.requireNonNull(email);
            Objects.requireNonNull(password);
            Objects.requireNonNull(userName);
        }
    }

    public sealed interface CreateAccountResult {
        record Success(UserId userId) implements CreateAccountResult {}
        record ExistingAccountFailure(String message) implements CreateAccountResult {}
        record PasswordPolicyFailure(String message) implements CreateAccountResult {}
        record InternalFailure(Throwable cause) implements CreateAccountResult {}
    }
}
