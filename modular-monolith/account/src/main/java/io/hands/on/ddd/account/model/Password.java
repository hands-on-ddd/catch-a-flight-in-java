package io.hands.on.ddd.account.model;

import io.hands.on.ddd.common.annotation.domain.DomainValueObject;

import java.util.Objects;

/**
 * Unencrypted password sent by user.
 * @param value plain text password passed from user interface to use case
 */
@DomainValueObject
public record Password(String value) {
    public Password {
        Objects.requireNonNull(value);
    }
}
