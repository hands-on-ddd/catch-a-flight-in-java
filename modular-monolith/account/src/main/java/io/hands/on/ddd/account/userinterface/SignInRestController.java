// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// -------------------------------------------------------------------------------------------------------------------------------------------------------------
package io.hands.on.ddd.account.userinterface;

import static io.hands.on.ddd.account.application.SignInUseCase.*;
import static io.hands.on.ddd.account.application.SignInUseCase.SignInResult.*;
import static io.hands.on.ddd.account.userinterface.RestResources.AUTHENTICATE_ACCOUNT;
import static io.hands.on.ddd.account.userinterface.SignInRestController.*;
import static io.pmarat.catchflight.common.controller.ResponseBodyHelper.badRequestBody;
import static io.pmarat.catchflight.common.controller.ResponseBodyHelper.internalServerBody;
import static org.springframework.http.ResponseEntity.status;

import io.hands.on.ddd.account.application.SignInUseCase;
import io.hands.on.hands.sharedkernel.UserId;
import io.pmarat.catchflight.common.annotations.hexagonal.WebAdapter;
import jakarta.servlet.http.HttpServletRequest;
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
 * REST API controller for POST /api/v1/account/auth endpoint.
 */
@Slf4j
@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping(AUTHENTICATE_ACCOUNT)
class SignInRestController {
  private static final String INCORRECT_CREDENTIALS = "Incorrect user and/or password.";

  private final SignInMapper signInMapper = new SignInMapper();
  private final SignInUseCase signInUseCase;
  private final HttpServletRequest servletRequest;

  @PostMapping
  ResponseEntity<?> signInUser(@Validated @RequestBody SignInRequest request) {
    log.info("Request: {}", request);
    var signInCommand = signInMapper.toCommand(request);
    var signInResult = signInUseCase.signIn(signInCommand);

    return switch (signInResult) {
      case Success(UserId userId) -> successBody(userId);
      case AuthenticationFailure _ -> badRequestBody(servletRequest, INCORRECT_CREDENTIALS);
      case InternalFailure(Throwable cause) -> internalServerBody(servletRequest, cause);
    };
  }

  // ---------------------------------------------------------------------------------------------------------------------------------------------------------
  // Private Section
  // ---------------------------------------------------------------------------------------------------------------------------------------------------------

  private static ResponseEntity<SignInResponse> successBody(UserId userId) {
    return status(HttpStatus.CREATED).body(new SignInResponse.SuccessResponse(userId));
  }

  // ---------------------------------------------------------------------------------------------------------------------------------------------------------
  // Static Types Section
  // ---------------------------------------------------------------------------------------------------------------------------------------------------------

  record SignInRequest(String email, String password) {}

  interface SignInResponse {
    record SuccessResponse(UserId userId) implements SignInResponse {}
  }

  private static class SignInMapper {
    SignInCommand toCommand(SignInRequest request) {
      return new SignInUseCase.SignInCommand();
    }
  }
}
