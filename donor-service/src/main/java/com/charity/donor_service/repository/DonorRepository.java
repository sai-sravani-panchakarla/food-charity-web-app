package com.charity.donor_service.repository;

import com.charity.donor_service.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface DonorRepository extends JpaRepository<Donor, Long> {
    List<Donor> findByMonth(String month);
    List<Donor> findByDonatedYear(int year);

    @Query("SELECT SUM(d.amount) FROM Donor d")
    Double getTotalDonations();

    @Query("SELECT SUM(d.amount) FROM Donor d WHERE d.month = :month AND d.donatedYear = :year")
    Double getTotalByMonthAndYear(String month, int year);
}
