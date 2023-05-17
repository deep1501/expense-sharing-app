package com.springboot.expensesharingapp.service;

import com.springboot.expensesharingapp.entity.User;

import java.util.Map;

public interface UserService {

    User createUser(User user);

    User getUser(Integer userIdd);

    void updateUserBalance(Integer userId, double amount);

    void updateUserBalances(Map<Integer, Double> balanceUpdates);
}
