package com.charity.expense_service.service;

import com.charity.expense_service.entity.Expense;
import com.charity.expense_service.exception.ResourceNotFoundException;
import com.charity.expense_service.repository.ExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExpenseService {

    private static final Logger log = LoggerFactory.getLogger(ExpenseService.class);

    @Autowired
    private ExpenseRepository repository;

    public List<Expense> getAllExpenses() {
        log.info("Fetching all expenses");
        List<Expense> expenses = repository.findAll();
        log.info("Found {} expenses", expenses.size());
        return expenses;
    }

    public List<Expense> getByMonth(String month) {
        log.info("Fetching expenses for month: {}", month);
        return repository.findByMonth(month);
    }

    public List<Expense> getByMonthAndYear(String month, int year) {
        log.info("Fetching expenses for month: {} year: {}", month, year);
        return repository.findByMonthAndExpenseYear(month, year);
    }

    public Double getTotalExpenses() {
        log.info("Fetching total expenses");
        return repository.getTotalExpenses();
    }

    public Double getTotalByMonthAndYear(String month, int year) {
        log.info("Fetching total expenses for month: {} year: {}", month, year);
        return repository.getTotalByMonthAndYear(month, year);
    }

    public Expense addExpense(Expense expense) {
        log.info("Adding new expense: {}", expense.getItemName());
        Expense saved = repository.save(expense);
        log.info("Expense saved with id: {}", saved.getId());
        return saved;
    }

    public void deleteExpense(Long id) {
        log.info("Deleting expense with id: {}", id);
        if (!repository.existsById(id)) {
            log.error("Expense not found with id: {}", id);
            throw new ResourceNotFoundException("Expense not found with id: " + id);
        }
        repository.deleteById(id);
        log.info("Expense deleted successfully with id: {}", id);
    }
}
