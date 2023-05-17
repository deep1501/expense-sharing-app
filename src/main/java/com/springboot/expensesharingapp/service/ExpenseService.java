package com.springboot.expensesharingapp.service;

import com.springboot.expensesharingapp.entity.Expense;

import java.util.Map;

public interface ExpenseService {

    Expense createExpense(Expense expense);
    Expense getExpense(Integer id);

    Map<String, Double> calculateBalance(Expense expense);
}
