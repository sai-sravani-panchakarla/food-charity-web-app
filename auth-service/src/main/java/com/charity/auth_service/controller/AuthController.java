package com.charity.auth_service.controller;

import com.charity.auth_service.dto.LoginRequest;
import com.charity.auth_service.dto.LoginResponse;
import com.charity.auth_service.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        log.info("POST /api/auth/login called");
        try {
            LoginResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401)
                .body(new LoginResponse(null, null, e.getMessage()));
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<Map<String, Boolean>> validate(@RequestBody Map<String, String> body) {
        String token = body.get("token");
        boolean valid = authService.validateToken(token);
        return ResponseEntity.ok(Map.of("valid", valid));
    }
}
