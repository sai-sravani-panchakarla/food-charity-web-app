package com.charity.donor_service.service;

import com.charity.donor_service.entity.Donor;
import com.charity.donor_service.exception.ResourceNotFoundException;
import com.charity.donor_service.repository.DonorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DonorService {

    private static final Logger log = LoggerFactory.getLogger(DonorService.class);

    @Autowired
    private DonorRepository repository;

    public List<Donor> getAllDonors() {
        log.info("Fetching all donors");
        List<Donor> donors = repository.findAll();
        log.info("Found {} donors", donors.size());
        return donors;
    }

    public List<Donor> getByMonth(String month) {
        log.info("Fetching donors for month: {}", month);
        return repository.findByMonth(month);
    }

    public Double getTotalDonations() {
        log.info("Fetching total donations");
        return repository.getTotalDonations();
    }

    public Double getTotalByMonthAndYear(String month, int year) {
        log.info("Fetching total donations for month: {} year: {}", month, year);
        return repository.getTotalByMonthAndYear(month, year);
    }

    public Donor addDonor(Donor donor) {
        log.info("Adding new donor: {}", donor.getDonorName());
        Donor saved = repository.save(donor);
        log.info("Donor saved with id: {}", saved.getId());
        return saved;
    }

    public void deleteDonor(Long id) {
        log.info("Deleting donor with id: {}", id);
        if (!repository.existsById(id)) {
            log.error("Donor not found with id: {}", id);
            throw new ResourceNotFoundException("Donor not found with id: " + id);
        }
        repository.deleteById(id);
        log.info("Donor deleted successfully with id: {}", id);
    }
}
