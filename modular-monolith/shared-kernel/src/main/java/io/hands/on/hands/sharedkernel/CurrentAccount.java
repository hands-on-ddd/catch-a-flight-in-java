package io.hands.on.hands.sharedkernel;

import io.hands.on.ddd.common.annotation.domain.DomainValueObject;

@DomainValueObject
public sealed interface CurrentAccount permits RegularAccount, PremiumAccount, NonExistingAccount {}
