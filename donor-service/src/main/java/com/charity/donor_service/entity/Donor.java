package com.charity.donor_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "donors")
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Donor name is required")
    @Size(min = 2, max = 100, message = "Donor name must be between 2 and 100 characters")
    private String donorName;

    @NotNull(message = "Amount is required")
    @Min(value = 1, message = "Amount must be at least 1")
    private Double amount;

    @NotNull(message = "Donated date is required")
    private LocalDate donatedDate;

    private String message;

    @NotBlank(message = "Month is required")
    private String month;

    @Min(value = 2020, message = "Year must be 2020 or later")
    private int donatedYear;
}
