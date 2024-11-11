// ---------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// ---------------------------------------------------------------------------------------------------------------------
package io.hands.on.ddd.account.userinterface;

import static io.hands.on.ddd.account.application.UpgradeAccountUseCase.UpgradeUserResult.*;
import static io.hands.on.ddd.account.userinterface.RestResources.UPGRADE_ACCOUNT;
import static io.pmarat.catchflight.common.controller.ResponseBodyHelper.badRequestBody;
import static io.pmarat.catchflight.common.controller.ResponseBodyHelper.internalServerBody;
import static org.springframework.http.ResponseEntity.status;

import io.hands.on.ddd.account.application.UpgradeAccountUseCase;
import io.hands.on.hands.sharedkernel.UserId;
import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/** REST API controller for POST /api/v1/account/{accountId}/upgrade endpoint. */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(UPGRADE_ACCOUNT)
class UpgradeAccountRestController {
  private final UpgradeUserMapper upgradeUserMapper = new UpgradeUserMapper();
  private final UpgradeAccountUseCase upgradeAccountUseCase;
  private final HttpServletRequest servletRequest;

  @PostMapping
  ResponseEntity<?> upgrade(@PathVariable("accountId") String accountId) {
    log.info("Request, userId: {}", accountId);
    var upgradeUserCommand = upgradeUserMapper.toCommand(accountId);
    var upgradeUserResult = upgradeAccountUseCase.upgradeUser(upgradeUserCommand);

    return switch (upgradeUserResult) {
      case Success() -> successBody();
      case UserNotFoundFailure(String message) -> badRequestBody(servletRequest, message);
      case UserAlreadyUpgradedFailure(String message) -> badRequestBody(servletRequest, message);
      case InternalFailure(Throwable cause) -> internalServerBody(servletRequest, cause);
    };
  }

  // ---------------------------------------------------------------------------------------------------------------------------------------------------------
  // Private Section
  // ---------------------------------------------------------------------------------------------------------------------------------------------------------

  private static ResponseEntity<UpgradeUserResponse> successBody() {
    return status(HttpStatus.CREATED).build();
  }

  // ---------------------------------------------------------------------------------------------------------------------------------------------------------
  // Static Types Section
  // ---------------------------------------------------------------------------------------------------------------------------------------------------------

  private interface UpgradeUserResponse {
    record SuccessResponse() implements UpgradeUserResponse {}
  }

  private static class UpgradeUserMapper {
    UpgradeAccountUseCase.UpgradeUserCommand toCommand(String userId) {
      return new UpgradeAccountUseCase.UpgradeUserCommand(new UserId(UUID.fromString(userId)));
    }
  }
}
