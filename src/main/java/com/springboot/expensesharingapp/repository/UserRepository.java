package com.springboot.expensesharingapp.repository;

import com.springboot.expensesharingapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
