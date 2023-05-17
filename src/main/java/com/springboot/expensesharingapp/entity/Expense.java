package com.springboot.expensesharingapp.entity;

import com.springboot.expensesharingapp.util.SplitStrategy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int expenseId;
    private String payer;
    private List<User> participants;
    private double amount;
    private double balances;
    private double exactAmounts;

    @Transient
    public int getParticipantCount() {
        return participants.size();
    }

    @Transient
    public double getExactAmount(){
        return exactAmounts;
    }

    @Transient
    public SplitStrategy getSplitStrategy() {
        SplitStrategy splitStrategy = null;
        return splitStrategy;
    }
}
