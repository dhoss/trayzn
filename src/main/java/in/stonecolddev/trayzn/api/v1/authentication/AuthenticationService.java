package in.stonecolddev.trayzn.api.v1.authentication;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {

  private final Logger log = LoggerFactory.getLogger(AuthenticationService.class);

  private static final String AUTH_TOKEN_HEADER_NAME = "X-API-Key";
  private static final String AUTH_SECRET_HEADER_NAME = "X-API-Secret";

  private final ApiAuthConfiguration config;

  public AuthenticationService(ApiAuthConfiguration config) {
    this.config = config;
  }

  public Authentication getAuthentication(HttpServletRequest request) {
    var apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);
    var apiSecret = request.getHeader(AUTH_SECRET_HEADER_NAME);
    if (
      apiKey == null ||
      !apiKey.equals(config.apiKey()) ||
      apiSecret == null ||
      !apiSecret.equals(config.apiSecret())) {

        log.error("Unsuccessful API request from {}", apiKey);
        throw new BadCredentialsException("Invalid API key/secret");

    }

    log.info("Successful API request from {}", apiKey);

    return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
  }
}