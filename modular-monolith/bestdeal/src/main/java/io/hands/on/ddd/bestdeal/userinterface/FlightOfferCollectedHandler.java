// ---------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// ---------------------------------------------------------------------------------------------------------------------
package io.hands.on.ddd.bestdeal.userinterface;

import io.hands.on.ddd.bestdeal.application.IndexFlightQueryUseCase;
import io.pmarat.catchflight.common.annotations.hexagonal.MessageListenerAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/** FlightOfferCollected event handler. */
@Slf4j
@MessageListenerAdapter
@RequiredArgsConstructor
class FlightOfferCollectedHandler {
  private final IndexFlightQueryUseCase indexFlightQueryUseCase;

  //    @EventListener
  //    void handleEvent(FlightOfferCollected flightOfferCollected) {
  //        log.info("Event consumed. Event Id: {}", flightOfferCollected.eventId());
  //    }
}
