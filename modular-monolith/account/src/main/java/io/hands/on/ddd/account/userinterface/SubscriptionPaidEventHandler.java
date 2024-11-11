// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// -------------------------------------------------------------------------------------------------------------------------------------------------------------
package io.hands.on.ddd.account.userinterface;

import io.hands.on.ddd.account.application.UpgradeAccountUseCase;
import io.hands.on.ddd.account.domain.event.AccountSubscriptionPaid;
import io.pmarat.catchflight.common.annotations.hexagonal.MessageListenerAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * UserSubscriptionPaid event handler.
 */
@MessageListenerAdapter
@RequiredArgsConstructor
class SubscriptionPaidEventHandler {
  private final UpgradeAccountUseCase upgradeAccountUseCase;

  @EventListener
  void handle(AccountSubscriptionPaid accountSubscriptionPaid) {
    throw new UnsupportedOperationException();
  }
}
