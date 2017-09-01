package com.axxes.timesheet.time.service;

import com.axxes.timesheet.time.domain.Project;
import com.axxes.timesheet.time.domain.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    void assignProjectToUser(Long userId, Project project);
    List<User> findUsersWithIncompletePeriod(LocalDate from, LocalDate to);
}
