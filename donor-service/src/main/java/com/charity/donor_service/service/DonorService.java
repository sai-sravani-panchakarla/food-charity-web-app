package com.charity.donor_service.service;

import com.charity.donor_service.entity.Donor;
import com.charity.donor_service.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DonorService {

    @Autowired
    private DonorRepository repository;

    public List<Donor> getAllDonors() {
        return repository.findAll();
    }

    public List<Donor> getByMonth(String month) {
        return repository.findByMonth(month);
    }

    public Double getTotalDonations() {
        return repository.getTotalDonations();
    }

    public Double getTotalByMonthAndYear(String month, int year) {
        return repository.getTotalByMonthAndYear(month, year);
    }

    public Donor addDonor(Donor donor) {
        return repository.save(donor);
    }

    public void deleteDonor(Long id) {
        repository.deleteById(id);
    }
}
