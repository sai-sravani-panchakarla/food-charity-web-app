package com.charity.expense_service.repository;

import com.charity.expense_service.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByMonth(String month);
    List<Expense> findByExpenseYear(int year);
    List<Expense> findByMonthAndExpenseYear(String month, int year);

    @Query("SELECT SUM(e.amount) FROM Expense e")
    Double getTotalExpenses();

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.month = :month AND e.expenseYear = :year")
    Double getTotalByMonthAndYear(String month, int year);
}
