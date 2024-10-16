package fr.ecf.arcadia.security.configuration;

import fr.ecf.arcadia.security.repository.TokenRepository;
import fr.ecf.arcadia.security.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    private final TokenRepository tokenRepository;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // if (request.getMethod().equals("OPTIONS")){
        //     filterChain.doFilter(request,response);
        //     return;
        // }
        
        // if (request.getMethod().equals("POST") && (
        //     request.getServletPath().equals("/api/views") || 
        //     request.getServletPath().equals("/api/animals/statistics")
        // )){
        //     filterChain.doFilter(request,response);
        //     return;
        // }
        
        // if (request.getServletPath().contains("/api/auth")){
        //     filterChain.doFilter(request,response);
        //     return;
        // }
        
        String authHeader = request.getHeader("authorization");
        String jwt = null;
        String userEmail = null;

        if (null != authHeader && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            try {
                userEmail = jwtService.extractUsername(jwt);
            } catch (ExpiredJwtException e ) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expiré !!");
                return;
            }
        }
        
        if((userEmail != null) && (SecurityContextHolder.getContext().getAuthentication() == null)) {
            
            if(tokenRepository.findByToken(jwt)
                .map(t -> t.isExpired())
                .orElse(false)) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token en base expiré ou révoqué !");
                    return;
            } else {
                
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
    
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }    

        }
            
        filterChain.doFilter(request,response);

    }

}
