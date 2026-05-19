package com.charity.expense_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private Double amount;
    private String category;
    private String month;
    private int expenseYear;
    private LocalDate expenseDate;
    private String notes;
}
