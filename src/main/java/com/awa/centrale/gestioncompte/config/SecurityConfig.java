package com.awa.centrale.gestioncompte.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration for JWT-based stateless API authentication.
 * This configuration enables a stateless, token-based authentication mechanism using JWT (JSON Web Tokens).
 * The application does not maintain server-side sessions; instead, each request is authenticated independently
 * using JWT tokens provided in the request headers.
 * Key security settings:
 * - CSRF protection is disabled (not needed for stateless APIs with JWT)
 * - HTTP Basic and form-based login are disabled (authentication is handled via JWT tokens)
 * - Session creation is disabled to enforce a stateless architecture
 * - All HTTP requests are permitted at the filter chain level; authorization is enforced at the service/controller level
 * - Password encoding uses BCrypt for secure password storage
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
		http
				.csrf(AbstractHttpConfigurer::disable)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.httpBasic(AbstractHttpConfigurer::disable)
				.formLogin(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(authorize -> authorize
						.anyRequest().permitAll()
				);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
