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
import java.time.LocalDate;
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
    public List<User> findUsersWithIncompletePeriod(LocalDate from, LocalDate to) {
        List<Entry> entries = entryRepository.findAllByFromBetween(from, to);
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        List<LocalDate> requiredDays = findRequiredDaysInPeriod(from, to);

        List<User> incomplete = new ArrayList<>();

        for (User u : users) {
            for (LocalDate l : requiredDays) {
                if (!entryExistsFromUserWithDate(u.getId(), l, entries)) {
                    incomplete.add(u);
                }
            }
        }
        return incomplete;
    }

    private List<LocalDate> findRequiredDaysInPeriod(LocalDate from, LocalDate to) {
        List<LocalDate> dates = new ArrayList<>();
        for (LocalDate date = from; date.isBefore(to); date = date.plusDays(1)) {
            if (!date.getDayOfWeek().equals(DayOfWeek.SATURDAY) && !date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                dates.add(date);
            }
        }
        return dates;
    }

    private boolean entryExistsFromUserWithDate(Long userId, LocalDate date, List<Entry> entries) {
        for (Entry e : entries) {
            if (e.getFrom().toLocalDate().equals(date) && e.getUser().getId().equals(userId)) {
                return true;
            }
        }
        return false;
    }
}
