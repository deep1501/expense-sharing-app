package com.springboot.expensesharingapp.service.Impl;

import com.springboot.expensesharingapp.entity.Expense;
import com.springboot.expensesharingapp.entity.User;
import com.springboot.expensesharingapp.repository.ExpenseRepository;
import com.springboot.expensesharingapp.service.ExpenseService;
import com.springboot.expensesharingapp.Exception.ResourceNotFoundException;
import com.springboot.expensesharingapp.util.SplitStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private static List<User> users= new ArrayList<>();

    static {
        users.add(new User(1,"Harry Potter", "harry@123", "9809806543"));
        users.add(new User(2,"Adam Smith", "harry@123", "9809806548"));
        users.add(new User(3,"Thomas Miller", "harry@123", "9809806540"));
        users.add(new User(4,"Tom Cruis", "harry@123", "9809806545"));
    }
    @Autowired
    private ExpenseRepository expenseRepo;
    @Override
    public Expense createExpense(Expense expense) {
        return expenseRepo.save(expense);
    }

    @Override
    public Expense getExpense(Integer id) {
        Expense expense = expenseRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));
        return expense;
    }

    @Override
    public Map<String, Double> calculateBalance(Expense expense) {
        Map<String, Double> balances = new HashMap<>();

        double totalAmount = expense.getAmount();
        int totalParticipants = expense.getParticipants().size();
        double perPersonAmount = totalAmount / totalParticipants;

        for (User user : users) {
            String name = user.getName();
            if (name.equals(expense.getPayer())) {
                balances.put(name, 0.0);
            } else {
                balances.put(name, perPersonAmount);
            }
        }

//        if (expense.getSplitStrategy().equals(SplitStrategy.EXACT)) {
//            for (Map.Entry<String, Double> exactAmountEntry : expense.getExactAmounts().entrySet()) {
//                String participant = exactAmountEntry.getKey();
//                double exactAmount = exactAmountEntry.getValue();
//                balances.put(participant, balances.get(participant) - exactAmount);
//            }
//        }

        return balances;
    }
}
