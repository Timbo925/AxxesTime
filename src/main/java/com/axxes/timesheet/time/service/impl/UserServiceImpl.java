package com.axxes.timesheet.time.service.impl;

import com.axxes.timesheet.time.domain.Entry;
import com.axxes.timesheet.time.domain.Project;
import com.axxes.timesheet.time.domain.User;
import com.axxes.timesheet.time.repository.EntryRepository;
import com.axxes.timesheet.time.repository.UserRepository;
import com.axxes.timesheet.time.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntryRepository entryRepository;

    @Override
    public void assignProjectToUser(Long userId, Project project) {
        User user = userRepository.findOne(userId);
        user.setCurrentProject(project);
        userRepository.save(user);
    }

    @Override
    public List<User> findUsersWithIncompletePeriod(LocalDate from, LocalDate to) {
        List<Entry> entries = entryRepository.findAllByDayBetween(from, to);
       // userRepository.findAll().forEach();
        return null;
    }
}
