// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// -------------------------------------------------------------------------------------------------------------------------------------------------------------
package io.hands.on.ddd.account.userinterface;

import static io.hands.on.ddd.account.application.CreateAccountUseCase.CreateAccountResult.*;
import static io.hands.on.ddd.account.userinterface.RestResources.CREATE_ACCOUNT;
import static io.pmarat.catchflight.common.controller.ResponseBodyHelper.badRequestBody;
import static io.pmarat.catchflight.common.controller.ResponseBodyHelper.internalServerBody;
import static org.springframework.http.ResponseEntity.status;

import io.hands.on.ddd.account.application.CreateAccountUseCase;
import io.hands.on.ddd.account.model.Password;
import io.hands.on.ddd.account.model.UserName;
import io.hands.on.hands.sharedkernel.Email;
import io.hands.on.hands.sharedkernel.UserId;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * REST API controller for POST /api/v1/account endpoint.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(CREATE_ACCOUNT)
class CreateAccountRestController {
  private final CreateAccountMapper createAccountMapper = new CreateAccountMapper();
  private final CreateAccountUseCase createAccountUseCase;
  private final HttpServletRequest servletRequest;

  @PostMapping
  ResponseEntity<?> createUser(@Validated @RequestBody CreateAccountRequest request) {
    log.info("Request: {}", request);
    var createUserCommand = createAccountMapper.toCommand(request);
    var createUserResult = createAccountUseCase.createUser(createUserCommand);

    return switch (createUserResult) {
      case Success(UserId userId) -> successBody(userId);
      case ExistingAccountFailure(String message) -> badRequestBody(servletRequest, message);
      case PasswordPolicyFailure(String message) -> badRequestBody(servletRequest, message);
      case InternalFailure(Throwable cause) -> internalServerBody(servletRequest, cause);
    };
  }

  // ---------------------------------------------------------------------------------------------------------------------------------------------------------
  // Private Section
  // ---------------------------------------------------------------------------------------------------------------------------------------------------------

  private static ResponseEntity<CreateAccountResponse> successBody(UserId userId) {
    return status(HttpStatus.CREATED).body(new CreateAccountResponse.SuccessResponse(userId));
  }

  // ---------------------------------------------------------------------------------------------------------------------------------------------------------
  // Static Types Section
  // ---------------------------------------------------------------------------------------------------------------------------------------------------------

  record CreateAccountRequest(
      @NotNull String email,
      @NotNull String password,
      @NotNull String firstName,
      @NotNull String lastName) {}

  interface CreateAccountResponse {
    record SuccessResponse(UserId userId) implements CreateAccountResponse {}
  }

  private static class CreateAccountMapper {
    CreateAccountUseCase.CreateAccountCommand toCommand(CreateAccountRequest request) {
      return new CreateAccountUseCase.CreateAccountCommand(
          new Email(request.email()),
          new Password(request.password()),
          new UserName(request.firstName(), request.lastName()));
    }
  }
}
