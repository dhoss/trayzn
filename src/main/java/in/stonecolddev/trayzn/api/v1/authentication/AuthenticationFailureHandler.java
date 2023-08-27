package in.stonecolddev.trayzn.api.v1.authentication;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;

public class AuthenticationFailureHandler implements org.springframework.security.web.authentication.AuthenticationFailureHandler {
  private final Logger log = LoggerFactory.getLogger(AuthenticationFailureHandler.class);

  @Override
  public void onAuthenticationFailure(
    HttpServletRequest request,
    HttpServletResponse response,
    AuthenticationException authenticationException) throws IOException, ServletException {
    log.error("***** AUTHENTICATION EXCEPTION HANDLER");

    response.sendError(401, "Unauthorized");
  }
}