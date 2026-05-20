package com.charity.auth_service.service;

import com.charity.auth_service.config.JwtUtil;
import com.charity.auth_service.dto.LoginRequest;
import com.charity.auth_service.dto.LoginResponse;
import com.charity.auth_service.entity.Admin;
import com.charity.auth_service.repository.AdminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) {
        log.info("Login attempt for username: {}", request.getUsername());

        Admin admin = adminRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> {
                    log.warn("User not found: {}", request.getUsername());
                    return new RuntimeException("Invalid username or password");
                });

        if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            log.warn("Invalid password for user: {}", request.getUsername());
            throw new RuntimeException("Invalid username or password");
        }

        String token = jwtUtil.generateToken(admin.getUsername());
        log.info("Login successful for user: {}", request.getUsername());
        return new LoginResponse(token, admin.getUsername(), "Login successful");
    }

    public Admin register(String username, String password) {
        log.info("Registering new admin: {}", username);
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(passwordEncoder.encode(password));
        return adminRepository.save(admin);
    }

    public boolean validateToken(String token) {
        return jwtUtil.validateToken(token);
    }
}
