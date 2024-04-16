// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

package io.hands.on.ddd.account.application;

import io.hands.on.ddd.common.annotation.hexagonal.InboundPort;
import io.hands.on.ddd.common.annotation.hexagonal.UseCase;
import io.hands.on.ddd.common.event.DomainEventsPublisher;
import io.hands.on.hands.sharedkernel.UserId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Use case for authenticating user.
 */
@Slf4j
@UseCase
@InboundPort
@RequiredArgsConstructor
public class SignInUseCase {
    private final DomainEventsPublisher domainEventsPublisher;

    @Transactional
    public SignInResult signIn(SignInCommand signInCommand) {
        throw new UnsupportedOperationException();
    }

    // ---------------------------------------------------------------------------------------------------------------------------------------------------------
    // Static Types Section†
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------

    public record SignInCommand() {}

    public sealed interface SignInResult {
        record Success(UserId userId) implements SignInResult {}
        record AuthenticationFailure() implements SignInResult {}
        record InternalFailure(Throwable cause) implements SignInResult {}
    }
}
