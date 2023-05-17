package com.springboot.expensesharingapp.service.Impl;

import com.springboot.expensesharingapp.entity.User;
import com.springboot.expensesharingapp.repository.UserRepository;
import com.springboot.expensesharingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;
    @Override
    public User createUser(User user) {
        return  userRepo.save(user);
    }

    @Override
    public User getUser(Integer userId) {
        return userRepo.findById(userId)
                .orElse(null);
    }

    @Override
    public void updateUserBalance(Integer userId, double amount) {
        User user = userRepo.findById(userId)
                .orElse(null);
        if (user != null) {
            double currentBalance = user.getBalance();
            double updatedBalance = currentBalance + amount;
            user.setBalance(updatedBalance);
            userRepo.save(user);
        }
    }

    @Override
    public void updateUserBalances(Map<Integer, Double> balanceUpdates) {
        for (Map.Entry<Integer, Double> entry : balanceUpdates.entrySet()) {
            int userId = entry.getKey();
            double amount = entry.getValue();
            updateUserBalance(userId, amount);
        }
    }
}
