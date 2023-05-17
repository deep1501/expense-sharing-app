package com.springboot.expensesharingapp.controller;

import com.springboot.expensesharingapp.entity.User;
import com.springboot.expensesharingapp.service.UserService;
import com.springboot.expensesharingapp.util.SplitStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ExpenseController {
    @Autowired
    private UserService userService;
    @Autowired
    private SplitStrategy splitStrategy;

//    public enum SplitStrategy {
//        EQUAL,
//        EXACT
//    }

    @PostMapping("/split")
    public ResponseEntity<String> splitExpense(@RequestParam Integer payerId,
                                               @RequestParam double amount,
                                               @RequestParam int numParticipants,
                                               @RequestParam List<Integer> participantIds,
                                               @RequestParam SplitStrategy splitStrategy,
                                               @RequestParam(required = false) List<Double> exactAmounts) {
        double splitAmount = amount / numParticipants;

        userService.updateUserBalance(payerId, -amount);

        if (splitStrategy == SplitStrategy.EQUAL) {
            for (Integer participantId : participantIds) {
                userService.updateUserBalance(participantId, splitAmount);
            }
        } else if (splitStrategy == SplitStrategy.EXACT && exactAmounts != null && exactAmounts.size() == numParticipants) {
            for (int i = 0; i < numParticipants; i++) {
                int participantId = participantIds.get(i);
                double exactAmount = exactAmounts.get(i);
                double balanceToUpdate = exactAmount - splitAmount;
                userService.updateUserBalance(participantId, balanceToUpdate);
            }
        } else {
            return ResponseEntity.badRequest().body("Invalid split strategy or missing exact amounts");
        }

        return ResponseEntity.ok("Expense split successfully");
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserBalance(@PathVariable Integer userId) {
        User user = userService.getUser(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}
