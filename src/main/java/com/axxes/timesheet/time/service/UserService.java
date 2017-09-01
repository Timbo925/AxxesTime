package com.axxes.timesheet.time.service;

import com.axxes.timesheet.time.domain.Project;
import com.axxes.timesheet.time.domain.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {

    void assignProjectToUser(Long userId, Project project);

    List<User> findUsersWithIncompletePeriod(LocalDateTime from, LocalDateTime to, Long projectId);

    void takeRecup(double amount, long userId);

    void takeVacation(double amount, long userId);
}
