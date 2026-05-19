package com.charity.donor_service.controller;

import com.charity.donor_service.entity.Donor;
import com.charity.donor_service.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/donors")
@CrossOrigin(origins = "*")
public class DonorController {

    @Autowired
    private DonorService service;

    @GetMapping
    public List<Donor> getAll() {
        return service.getAllDonors();
    }

    @GetMapping("/month/{month}")
    public List<Donor> getByMonth(@PathVariable String month) {
        return service.getByMonth(month);
    }

    @GetMapping("/total")
    public Double getTotalDonations() {
        return service.getTotalDonations();
    }

    @GetMapping("/total/filter")
    public Double getTotalByMonthAndYear(
            @RequestParam String month,
            @RequestParam int year) {
        return service.getTotalByMonthAndYear(month, year);
    }

    @PostMapping
    public Donor addDonor(@RequestBody Donor donor) {
        return service.addDonor(donor);
    }

    @DeleteMapping("/{id}")
    public String deleteDonor(@PathVariable Long id) {
        service.deleteDonor(id);
        return "Deleted successfully";
    }
}
