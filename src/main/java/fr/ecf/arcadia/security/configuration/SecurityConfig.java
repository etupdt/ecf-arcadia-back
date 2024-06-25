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
        .securityMatcher("/api/auth/authenticate", "/api/auth/request-token", "/api/animals/statistics", "/api/contact")  
        .authorizeHttpRequests(auth -> {
            auth
            .requestMatchers(HttpMethod.POST).permitAll()
            .requestMatchers(HttpMethod.GET).hasAuthority("ADMIN");
        })
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        ;

        return httpSecurity.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChainUsers(HttpSecurity httpSecurity) throws Exception {
        // sharedSecurityConfiguration(httpSecurity);
        httpSecurity
        .csrf(csrf -> csrf.disable())
        .securityMatcher(
            "/api/animals", "/api/animals/*", "/api/animals/statistics",
            "/api/foods", "/api/foods/*",
            "/api/habitats", "/api/habitats/*",
            "/api/hours", "/api/hours/*",
            "/api/breeds", "/api/breeds/*",
            "/api/services", "/api/services/*",
            "/api/users", "/api/users/*"
            )
        .authorizeHttpRequests(auth -> {
            auth
            .requestMatchers(HttpMethod.GET).permitAll()
            .requestMatchers(HttpMethod.POST).hasAuthority("ADMIN")
            .requestMatchers(HttpMethod.PUT).hasAuthority("ADMIN")
            .requestMatchers(HttpMethod.DELETE).hasAuthority("ADMIN")
            .anyRequest().permitAll();
        })
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        ;

        return httpSecurity.build();
    }

    @Bean
    @Order(3)
    public SecurityFilterChain securityFilterChainViews(HttpSecurity httpSecurity) throws Exception {
        // sharedSecurityConfiguration(httpSecurity);
        httpSecurity
        .csrf(csrf -> csrf.disable())
        .securityMatcher("/api/views", "/api/views/*")
        .authorizeHttpRequests(auth -> {
            auth
            .requestMatchers(HttpMethod.GET).permitAll()
            .requestMatchers(HttpMethod.POST).permitAll()
            .requestMatchers(HttpMethod.PUT).hasAuthority("EMPLOYEE")
            .requestMatchers(HttpMethod.DELETE).hasAuthority("EMPLOYEE")
            .anyRequest().permitAll();
        })
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        ;

        return httpSecurity.build();
    }

    @Bean
    @Order(4)
    public SecurityFilterChain securityFilterChainEmployee(HttpSecurity httpSecurity) throws Exception {
        // sharedSecurityConfiguration(httpSecurity);
        httpSecurity
        .csrf(csrf -> csrf.disable())
        .securityMatcher("/api/foodanimals", "/api/foodanimals/*") 
        .authorizeHttpRequests(auth -> {
            auth
            .requestMatchers("api/**").hasAuthority("EMPLOYEE")
            .anyRequest().permitAll();
        })
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        ;

        return httpSecurity.build();
    }

    @Bean
    @Order(5)
    public SecurityFilterChain securityFilterChainVeterinary(HttpSecurity httpSecurity) throws Exception {
        // sharedSecurityConfiguration(httpSecurity);
        httpSecurity
        .csrf(csrf -> csrf.disable())
        .securityMatcher("/api/veterinaryreports", "/api/veterinaryreports/*")  
        .authorizeHttpRequests(auth -> {
            auth
            .requestMatchers("api/**").hasAuthority("VETERINARY").anyRequest().permitAll();
        })
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        ;

        return httpSecurity.build();
    }

    @Bean
    @Order
    public SecurityFilterChain securityFilterChainOther(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .securityMatcher("/api/**")
                .authorizeHttpRequests(auth -> {
                    auth.anyRequest().denyAll();
                })
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
    
}
