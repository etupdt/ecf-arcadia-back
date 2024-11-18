package fr.ecf.arcadia.security.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
    
    
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final LogoutHandler logoutHandler;

    private final AuthenticationProvider authenticationProvider;

    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChainVisitor(HttpSecurity httpSecurity) throws Exception {
        // sharedSecurityConfiguration(httpSecurity);
        httpSecurity
        .csrf(csrf -> csrf.disable())
        .securityMatcher("/auth/refresh-token", "/api/animals/statistics", "/api/contact")  
        .authorizeHttpRequests(auth -> {
            auth
            .requestMatchers(HttpMethod.POST).permitAll()
            .requestMatchers(HttpMethod.GET).hasAuthority("ADMIN");
        })
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        ;
        this.logger.info("==========================================>>>>>>><<<<<<<<<<<<<===========================");

        return httpSecurity.build();
    }

    @Bean
    @Order
    public SecurityFilterChain securityFilterPermitAll(HttpSecurity httpSecurity) throws Exception {
        // sharedSecurityConfiguration(httpSecurity);
        httpSecurity
        .csrf(csrf -> csrf.disable())
        .securityMatcher("/api/**", "/auth/authenticate")
        .authorizeHttpRequests(auth -> {
            auth
            .anyRequest().permitAll();
        })
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        ;
        this.logger.error("==========================================>>>>>>><<<<<<<<<<<<<===========================");
        
        return httpSecurity.build();
    }
    
}
