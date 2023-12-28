package in.stonecolddev.trayzn.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Clock;

@Configuration
@Profile({"local", "local-docker", "unit-test", "it-test", "dev", "prod"})
public class ClockConfiguration {

  @Bean
  public Clock clock() {
    return Clock.systemDefaultZone();
  }

}