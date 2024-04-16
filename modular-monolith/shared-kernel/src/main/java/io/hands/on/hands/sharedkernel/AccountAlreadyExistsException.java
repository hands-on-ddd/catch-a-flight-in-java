// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

package io.hands.on.hands.sharedkernel;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

public class AccountAlreadyExistsException extends RuntimeException {
    private static final String MESSAGE = "User already exists: %s";
    private final UserId userId;

    public AccountAlreadyExistsException(UserId userId) {
        this.userId = userId;
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE, userId.value());
    }
}
