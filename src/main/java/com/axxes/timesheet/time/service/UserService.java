package com.axxes.timesheet.time.service;

import com.axxes.timesheet.time.domain.Project;

public interface UserService {

    void assignProjectToUser(Long userId, Project project);
}
