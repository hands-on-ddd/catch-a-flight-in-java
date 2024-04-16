package io.hands.on.ddd.account.model;

/**
 * Signals too weak password when user's account is being created.
 */
public class PasswordPolicyException extends RuntimeException {
    public PasswordPolicyException(String message) {
        super(message);
    }
}
