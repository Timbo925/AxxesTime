package com.axxes.timesheet.time.service;

import com.axxes.timesheet.time.domain.Contract;
import com.axxes.timesheet.time.domain.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {

    void assignProjectToUser(Long userId, Contract contract);

    void takeRecup(double amount, long userId);

    List<User> findUsersWithIncompletePeriod(LocalDateTime from, LocalDateTime to);
    void takeVacation(double amount, long userId);

    User get(Long userId);

    void delete(Long userId);
}
