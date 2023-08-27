package in.stonecolddev.trayzn.api.v1.authentication;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;

import java.io.IOException;

public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {
  private final Logger log = LoggerFactory.getLogger(AccessDeniedHandler.class);

  @Override
  public void handle(
    HttpServletRequest request,
    HttpServletResponse response,
    AccessDeniedException accessDeniedException) throws IOException, ServletException {
    log.error("***** ACCESS DENIED HANDLER");

    response.sendError(403, "Access Denied");
  }
}