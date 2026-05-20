package com.charity.auth_service.config;

import com.charity.auth_service.repository.AdminRepository;
import com.charity.auth_service.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AuthService authService;

    @Override
    public void run(String... args) {
        if (adminRepository.findByUsername("admin").isEmpty()) {
            authService.register("admin", "admin123");
            log.info("Default admin created — username: admin, password: admin123");
        } else {
            log.info("Admin already exists, skipping creation");
        }
    }
}
