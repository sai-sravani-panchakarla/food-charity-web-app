package com.charity.donor_service.controller;

import com.charity.donor_service.entity.Donor;
import com.charity.donor_service.service.DonorService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/donors")
@CrossOrigin(origins = "*")
public class DonorController {

    private static final Logger log = LoggerFactory.getLogger(DonorController.class);

    @Autowired
    private DonorService service;

    @GetMapping
    public ResponseEntity<List<Donor>> getAll() {
        log.info("GET /api/donors called");
        return ResponseEntity.ok(service.getAllDonors());
    }

    @GetMapping("/month/{month}")
    public ResponseEntity<List<Donor>> getByMonth(@PathVariable String month) {
        log.info("GET /api/donors/month/{} called", month);
        return ResponseEntity.ok(service.getByMonth(month));
    }

    @GetMapping("/total")
    public ResponseEntity<Double> getTotalDonations() {
        log.info("GET /api/donors/total called");
        return ResponseEntity.ok(service.getTotalDonations());
    }

    @GetMapping("/total/filter")
    public ResponseEntity<Double> getTotalByMonthAndYear(
            @RequestParam String month, @RequestParam int year) {
        return ResponseEntity.ok(service.getTotalByMonthAndYear(month, year));
    }

    @PostMapping
    public ResponseEntity<Donor> addDonor(@Valid @RequestBody Donor donor) {
        log.info("POST /api/donors called for donor: {}", donor.getDonorName());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addDonor(donor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDonor(@PathVariable Long id) {
        log.info("DELETE /api/donors/{} called", id);
        service.deleteDonor(id);
        return ResponseEntity.ok("Donor deleted successfully");
    }
}
