// ---------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// ---------------------------------------------------------------------------------------------------------------------
package io.hands.on.ddd.bestdeal.application;

import io.hands.on.ddd.common.annotation.hexagonal.InboundPort;
import io.hands.on.ddd.common.annotation.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/** Use case for indexing best deals after flight query is made by any user. */
@Slf4j
@UseCase
@InboundPort
@RequiredArgsConstructor
public class IndexFlightQueryUseCase {
  @Transactional
  public IndexFlightQueryResult indexFlightQuery(IndexFlightQueryCommand indexFlightQueryCommand) {
    throw new UnsupportedOperationException();
  }

  // ---------------------------------------------------------------------------------------------------------------------------------------------------------
  // Static Types Section
  // ---------------------------------------------------------------------------------------------------------------------------------------------------------

  public record IndexFlightQueryCommand() {}

  public interface IndexFlightQueryResult {}
}
