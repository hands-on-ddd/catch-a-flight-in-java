// ---------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// ---------------------------------------------------------------------------------------------------------------------
package io.hands.on.ddd.account.userinterface;

// ---------------------------------------------------------------------------------------------------------------------
// Implementation
// ---------------------------------------------------------------------------------------------------------------------

/** REST endpoint names (for all controllers in module). */
class RestResources {
  private static final String API_PREFIX = "/api/v1";
  public static final String ACCOUNT = API_PREFIX + "/account";
  public static final String CREATE_ACCOUNT = ACCOUNT;
  public static final String AUTHENTICATE_ACCOUNT = ACCOUNT + "/auth";
  public static final String UPGRADE_ACCOUNT = ACCOUNT + "/{accountId}/upgrade";
}
