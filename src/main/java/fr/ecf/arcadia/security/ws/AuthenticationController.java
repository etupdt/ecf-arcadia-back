package fr.ecf.arcadia.security.ws;

import fr.ecf.arcadia.security.model.AuthenticationRequest;
import fr.ecf.arcadia.security.model.AuthenticationResponse;
import fr.ecf.arcadia.security.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

// @CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    
    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    // @PostMapping("/register")
    // public ResponseEntity<AuthenticationResponse> register(
    //         @RequestBody RegisterRequest request
    // ) {
    //     return ResponseEntity.ok(service.register(request));
    // }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
        @RequestBody AuthenticationRequest request,
        HttpServletResponse response
    ) throws IOException {
            return ResponseEntity.ok(service.authenticate(request, response));
        }
        
    @PostMapping("/refresh-token")
    public void refreshToken(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        logger.info("==========================> ws register ========= " + request.getContentLength());
        service.refreshToken(request, response);
    }
}
