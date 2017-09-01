package com.axxes.timesheet.time.service.impl;

import com.axxes.timesheet.time.domain.Entry;
import com.axxes.timesheet.time.domain.Project;
import com.axxes.timesheet.time.domain.User;
import com.axxes.timesheet.time.repository.EntryRepository;
import com.axxes.timesheet.time.repository.UserRepository;
import com.axxes.timesheet.time.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public List<User> findUsersWithIncompletePeriod(LocalDateTime from, LocalDateTime to, Long projectId) {
        List<Entry> entries = entryRepository.getEntriesBetweenFor(from, to, projectId);
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        List<LocalDateTime> requiredDays = findRequiredDaysInPeriod(from, to);

        List<User> incomplete = new ArrayList<>();

        for (User u : users) {
            for (LocalDateTime l : requiredDays) {
                if (!entryExistsFromProjectWithDate(u.getId(), l, entries)) {
                    incomplete.add(u);
                }
            }
        }
        return incomplete;
    }

    private List<LocalDateTime> findRequiredDaysInPeriod(LocalDateTime from, LocalDateTime to) {
        List<LocalDateTime> dates = new ArrayList<>();
        for (LocalDateTime date = from; date.isBefore(to); date = date.plusDays(1)) {
            if (!date.getDayOfWeek().equals(DayOfWeek.SATURDAY) && !date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                dates.add(date);
            }
        }
        return dates;
    }

    private boolean entryExistsFromProjectWithDate(Long projectId, LocalDateTime date, List<Entry> entries) {
        for (Entry e : entries) {
            if (e.getFrom().equals(date) && e.getProject().getId().equals(projectId)) {
                return true;
            }
        }
        return false;
    }
}
