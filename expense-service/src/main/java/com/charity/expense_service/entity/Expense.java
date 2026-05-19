package com.charity.expense_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Item name is required")
    @Size(min = 2, max = 100, message = "Item name must be between 2 and 100 characters")
    private String itemName;

    @NotNull(message = "Amount is required")
    @Min(value = 1, message = "Amount must be at least 1")
    private Double amount;

    @NotBlank(message = "Category is required")
    private String category;

    @NotBlank(message = "Month is required")
    private String month;

    @Min(value = 2020, message = "Year must be 2020 or later")
    private int expenseYear;

    @NotNull(message = "Expense date is required")
    private LocalDate expenseDate;

    private String notes;
}
