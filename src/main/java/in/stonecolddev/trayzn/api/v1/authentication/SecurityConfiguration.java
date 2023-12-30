package in.stonecolddev.trayzn.api.v1.authentication;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  private final ApplicationContext applicationContext;

  public SecurityConfiguration(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  // TODO: the security configuration stuff is insanely annoying, clean up somehow
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // TODO: add CORS test
    http.cors(cors -> cors.configurationSource(request -> {
              CorsConfiguration configuration = new CorsConfiguration();
              configuration.setAllowedOrigins(List.of("*"));
              configuration.setAllowedMethods(List.of("*"));
              configuration.setAllowedHeaders(List.of("*"));
              return configuration;
            }))
      .csrf(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(
        (authorizeHttpRequests) ->
          authorizeHttpRequests.requestMatchers("/**").authenticated())
      .httpBasic(withDefaults())
      .sessionManagement(
        (sessionManagement) ->
          sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .addFilterBefore(
        new AuthenticationFilter(applicationContext.getBean(AuthenticationService.class)),
        UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}