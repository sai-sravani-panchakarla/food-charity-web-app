package com.charity.donor_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "donors")
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String donorName;
    private Double amount;
    private LocalDate donatedDate;
    private String message;
    private String month;
    private int donatedYear;
}
