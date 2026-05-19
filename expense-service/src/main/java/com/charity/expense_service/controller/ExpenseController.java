package com.charity.expense_service.controller;

import com.charity.expense_service.entity.Expense;
import com.charity.expense_service.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController {

    @Autowired
    private ExpenseService service;

    @GetMapping
    public List<Expense> getAll() {
        return service.getAllExpenses();
    }

    @GetMapping("/month/{month}")
    public List<Expense> getByMonth(@PathVariable String month) {
        return service.getByMonth(month);
    }

    @GetMapping("/filter")
    public List<Expense> getByMonthAndYear(
            @RequestParam String month,
            @RequestParam int year) {
        return service.getByMonthAndYear(month, year);
    }

    @GetMapping("/total")
    public Double getTotalExpenses() {
        return service.getTotalExpenses();
    }

    @GetMapping("/total/filter")
    public Double getTotalByMonthAndYear(
            @RequestParam String month,
            @RequestParam int year) {
        return service.getTotalByMonthAndYear(month, year);
    }

    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        return service.addExpense(expense);
    }

    @DeleteMapping("/{id}")
    public String deleteExpense(@PathVariable Long id) {
        service.deleteExpense(id);
        return "Deleted successfully";
    }
}
