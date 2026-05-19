package com.charity.expense_service.service;

import com.charity.expense_service.entity.Expense;
import com.charity.expense_service.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository repository;

    public List<Expense> getAllExpenses() {
        return repository.findAll();
    }

    public List<Expense> getByMonth(String month) {
        return repository.findByMonth(month);
    }

    public List<Expense> getByMonthAndYear(String month, int year) {
        return repository.findByMonthAndExpenseYear(month, year);
    }

    public Double getTotalExpenses() {
        return repository.getTotalExpenses();
    }

    public Double getTotalByMonthAndYear(String month, int year) {
        return repository.getTotalByMonthAndYear(month, year);
    }

    public Expense addExpense(Expense expense) {
        return repository.save(expense);
    }

    public void deleteExpense(Long id) {
        repository.deleteById(id);
    }
}
