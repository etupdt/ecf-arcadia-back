package fr.ecf.arcadia.security.configuration;

import fr.ecf.arcadia.security.repository.TokenRepository;
import fr.ecf.arcadia.security.service.JwtService;
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
import java.nio.file.AccessDeniedException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    private final TokenRepository tokenRepository;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getMethod().equals("OPTIONS")){
            filterChain.doFilter(request,response);
            return;
        }
        
        if (request.getMethod().equals("GET")){
            filterChain.doFilter(request,response);
            return;
        }
        
        if (request.getMethod().equals("POST") && (
            request.getServletPath().equals("/api/views") || 
            request.getServletPath().equals("/api/animals/statistics")
        )){
            filterChain.doFilter(request,response);
            return;
        }
        
        if (request.getServletPath().contains("/api/auth")){
            filterChain.doFilter(request,response);
            return;
        }
        
        String authHeader = request.getHeader("authorization");
        String jwt= "";
        String userEmail = "";

        if (null != authHeader) {

            if (!authHeader.startsWith("Bearer ")) {
                throw new AccessDeniedException("Accés non autorisé");
            }

            jwt = authHeader.substring(7);
            userEmail = jwtService.extractUsername(jwt);

            if((userEmail != null) && (SecurityContextHolder.getContext().getAuthentication() == null)) {
                
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                boolean isTokenValid = tokenRepository.findByToken(jwt)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);
                
                if(jwtService.isTokenValid(jwt,userDetails) && isTokenValid) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }

            }
            
        }    

        filterChain.doFilter(request,response);

    }

}
