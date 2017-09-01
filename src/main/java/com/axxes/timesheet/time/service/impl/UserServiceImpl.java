package com.axxes.timesheet.time.service.impl;

import com.axxes.timesheet.time.domain.Contract;
import com.axxes.timesheet.time.domain.Entry;
import com.axxes.timesheet.time.domain.User;
import com.axxes.timesheet.time.repository.EntryRepository;
import com.axxes.timesheet.time.repository.UserRepository;
import com.axxes.timesheet.time.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
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
    public void assignProjectToUser(Long userId, Contract contract) {
        User user = userRepository.findOne(userId);
        user.getContracts().add(contract);
        userRepository.save(user);
    }

    @Override
    public List<User> findUsersWithIncompletePeriod(LocalDate from, LocalDate to) {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);

        List<User> incomplete = new ArrayList<>();

        for (User u : users) {
            List<Entry> entriesFromUser = new ArrayList<>();
            for (Contract p : u.getContracts()) {
                entriesFromUser.addAll(entryRepository.findAllByFromBetweenAndContractId(from.atStartOfDay(), to.plusDays(1).atStartOfDay(), p.getId()));
            }
            if (!periodContainRequiredDays(entriesFromUser, from.atStartOfDay(), to.plusDays(1).atStartOfDay())) {
                incomplete.add(u);
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

    private boolean periodContainRequiredDays(List<Entry> entries, LocalDateTime from, LocalDateTime to) {
        List<LocalDate> requiredDates = findRequiredDaysInPeriod(from.toLocalDate(), to.toLocalDate());
        for (LocalDate l : requiredDates) {
            if (!hasRequiredDay(entries, l)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasRequiredDay(List<Entry> entries, LocalDate date) {
        for (Entry e : entries) {
            if (e.getFrom().toLocalDate().equals(date)) {
                return true;
            }
        }
        return false;
    }
}
