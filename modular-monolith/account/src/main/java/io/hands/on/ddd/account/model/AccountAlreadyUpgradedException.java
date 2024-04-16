package io.hands.on.ddd.account.model;

import io.hands.on.hands.sharedkernel.UserId;

/**
 * Thrown when user is already of Premium type, so it cannot be upgraded.
 */
public class AccountAlreadyUpgradedException extends RuntimeException {
    private final UserId userId;

    public AccountAlreadyUpgradedException(UserId userId, String message) {
        super(message);
        this.userId = userId;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "[" + userId.value() + "]";
    }
}
