// ---------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// ---------------------------------------------------------------------------------------------------------------------
package io.hands.on.ddd.bestdeal.userinterface;

import io.hands.on.ddd.bestdeal.application.IndexBestDealsUseCase;
import io.pmarat.catchflight.common.annotations.hexagonal.SchedulerAdapter;
import lombok.RequiredArgsConstructor;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/** Periodically executed best deals indexing job. */
@SchedulerAdapter
@RequiredArgsConstructor
class IndexBestDealsScheduler {
  private final IndexBestDealsUseCase indexBestDealsUseCase;
}
