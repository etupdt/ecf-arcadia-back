package fr.ecf.arcadia.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ecf.arcadia.security.model.*;
import fr.ecf.arcadia.pojo.User;
import fr.ecf.arcadia.security.repository.TokenRepository;
import io.jsonwebtoken.ExpiredJwtException;
import fr.ecf.arcadia.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
    
    public AuthenticationResponse authenticate(
        AuthenticationRequest request,
        HttpServletResponse response
    ) throws IOException {

        logger.info("==========================> service authenticate ========= ");

        try {

            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
            );
            var user = repository.findByEmail(request.getEmail())
                .orElse(null);
            var jwtToken = jwtService.generateToken(user);    
            var refreshToken = jwtService.generateRefreshToken(user);
            revokeAllUserTokens(user);
            saveUserToken(user, jwtToken);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        } catch (InternalAuthenticationServiceException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Email ou mot de passe incorrect !");
            return null;
        } catch (BadCredentialsException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Email ou mot de passe incorrect !");
            return null;
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Erreur lors de l'authentification !");
            return null;
        }

    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Refresh token absent !");
        }
        refreshToken = authHeader.substring(7);
        userEmail = null;
        try {
            userEmail = jwtService.extractUsername(refreshToken);
        } catch (ExpiredJwtException e) {
            this.logger.info("==============================================> Exception catch : refreshToken : " + e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Email ou mot de passe incorrect !");
        }
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user.getUsername()) && !jwtService.isTokenExpired(refreshToken)) {
                var accessToken = jwtService.generateToken(user);
                var newRefreshToken = jwtService.generateRefreshToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(newRefreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
