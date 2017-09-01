package com.axxes.timesheet.time.service;

import com.axxes.timesheet.time.domain.Contract;
import com.axxes.timesheet.time.domain.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    void assignProjectToUser(Long userId, Contract contract);
    List<User> findUsersWithIncompletePeriod(LocalDate from, LocalDate to);
}
