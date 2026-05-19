package com.charity.food_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Food item name is required")
    @Size(min = 2, max = 100, message = "Item name must be between 2 and 100 characters")
    private String itemName;

    @NotBlank(message = "Description is required")
    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;

    @NotBlank(message = "Month is required")
    private String month;

    @Min(value = 2020, message = "Year must be 2020 or later")
    @Max(value = 2100, message = "Year must be valid")
    private int servedYear;

    @NotBlank(message = "Served to is required")
    private String servedTo;

    @NotBlank(message = "Cooked by is required")
    private String cookedBy;
}
