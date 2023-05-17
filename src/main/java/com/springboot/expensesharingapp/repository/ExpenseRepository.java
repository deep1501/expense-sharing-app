package com.springboot.expensesharingapp.repository;

import com.springboot.expensesharingapp.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
}
