package com.axxes.timesheet.time.service.impl;

import com.axxes.timesheet.time.domain.Contract;
import com.axxes.timesheet.time.domain.Entry;
import com.axxes.timesheet.time.domain.User;
import com.axxes.timesheet.time.repository.ContractRepository;
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

    @Autowired
    private ContractRepository contractRepository;

    @Override
    public void assignProjectToUser(Long userId, Contract contract) {
        User user = userRepository.findOne(userId);
        contractRepository.save(contract);
        user.getContracts().add(contract);
        userRepository.save(user);
    }

    @Override
    public List<User> findUsersWithIncompletePeriod(LocalDateTime begin, LocalDateTime to) {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);

        List<User> incomplete = new ArrayList<>();

        for (User u : users) {
            List<Entry> entriesFromUser = new ArrayList<>();
            for (Contract p : u.getContracts()) {
                entriesFromUser.addAll(entryRepository.findByContractIdAndBeginBetween(begin, to.plusDays(1), p.getId()));
            }
            if (!periodContainsRequiredDays(entriesFromUser, begin, to.plusDays(1))) {
                incomplete.add(u);
            }
        }
        return incomplete;
    }

    @Override
    public void takeRecup(double amount, long userId) {
        User user = userRepository.findOne(userId);
        user.substractRecup(amount);
    }

    @Override
    public void takeVacation(double amount, long userId) {
        User user = userRepository.findOne(userId);
        user.substractVacation(amount);
    }

    @Override
    public User get(Long userId) {

        return userRepository.findOne(userId);
    }

    @Override
    public void delete(Long userId) {
        userRepository.delete(userRepository.findOne(userId));
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User get(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
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

    private boolean periodContainsRequiredDays(List<Entry> entries, LocalDateTime from, LocalDateTime to) {
        List<LocalDateTime> requiredDates = findRequiredDaysInPeriod(from, to);
        for (LocalDateTime l : requiredDates) {
            if (!hasRequiredDay(entries, l.toLocalDate())) {
                return false;
            }
        }
        return true;
    }

    private boolean hasRequiredDay(List<Entry> entries, LocalDate date) {
        for (Entry e : entries) {
            if (e.getBegin().toLocalDate().equals(date)) {
                return true;
            }
        }
        return false;
    }
}
