package in.stonecolddev.trayzn.api.v1.authentication;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

@ConfigurationProperties(prefix = "api.authentication")
@Profile({"local", "unit-test", "it-test", "dev", "prod"})
public record ApiAuthConfiguration(
  String apiKey,
  String apiSecret
){}