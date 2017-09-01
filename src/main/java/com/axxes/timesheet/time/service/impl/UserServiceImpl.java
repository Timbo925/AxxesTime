package com.axxes.timesheet.time.service.impl;

import com.axxes.timesheet.time.domain.Project;
import com.axxes.timesheet.time.domain.User;
import com.axxes.timesheet.time.repository.UserRepository;
import com.axxes.timesheet.time.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void assignProjectToUser(Long userId, Project project) {
        User user = userRepository.findOne(userId);
        user.setCurrentProject(project);
        userRepository.save(user);
    }
}
