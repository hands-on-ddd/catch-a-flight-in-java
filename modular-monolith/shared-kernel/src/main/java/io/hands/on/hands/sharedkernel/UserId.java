package io.hands.on.hands.sharedkernel;

import io.hands.on.ddd.common.annotation.domain.DomainValueObject;

import java.util.UUID;

@DomainValueObject
public record UserId(UUID value) {}
