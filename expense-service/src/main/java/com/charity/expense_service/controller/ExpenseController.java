package com.charity.expense_service.controller;

import com.charity.expense_service.entity.Expense;
import com.charity.expense_service.service.ExpenseService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController {

    private static final Logger log = LoggerFactory.getLogger(ExpenseController.class);

    @Autowired
    private ExpenseService service;

    @GetMapping
    public ResponseEntity<List<Expense>> getAll() {
        log.info("GET /api/expenses called");
        return ResponseEntity.ok(service.getAllExpenses());
    }

    @GetMapping("/month/{month}")
    public ResponseEntity<List<Expense>> getByMonth(@PathVariable String month) {
        log.info("GET /api/expenses/month/{} called", month);
        return ResponseEntity.ok(service.getByMonth(month));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Expense>> getByMonthAndYear(
            @RequestParam String month, @RequestParam int year) {
        return ResponseEntity.ok(service.getByMonthAndYear(month, year));
    }

    @GetMapping("/total")
    public ResponseEntity<Double> getTotalExpenses() {
        log.info("GET /api/expenses/total called");
        return ResponseEntity.ok(service.getTotalExpenses());
    }

    @GetMapping("/total/filter")
    public ResponseEntity<Double> getTotalByMonthAndYear(
            @RequestParam String month, @RequestParam int year) {
        return ResponseEntity.ok(service.getTotalByMonthAndYear(month, year));
    }

    @PostMapping
    public ResponseEntity<Expense> addExpense(@Valid @RequestBody Expense expense) {
        log.info("POST /api/expenses called for item: {}", expense.getItemName());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addExpense(expense));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
        log.info("DELETE /api/expenses/{} called", id);
        service.deleteExpense(id);
        return ResponseEntity.ok("Expense deleted successfully");
    }
}
