package com.axxes.timesheet.time.service;

import com.axxes.timesheet.time.domain.Entry;
import com.axxes.timesheet.time.domain.Percentage;
import com.axxes.timesheet.time.domain.Status;
import com.axxes.timesheet.time.domain.User;
import com.axxes.timesheet.time.repository.EntryRepository;
import com.axxes.timesheet.time.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public void createEntryForUser(LocalTime from, LocalTime to, LocalDateTime day, Long userId, Percentage percentage) {
        User user = userRepository.findOne(userId);
        Entry entry = new Entry(from, to, day);

        entry.setUser(user);
        entry.setProject(user.getCurrentProject());
        entry.setPercentage(percentage);

        entry.setStatus(Status.OPEN);

        entryRepository.save(entry);
    }

    @Override
    public void savePeriodAsUser(LocalDateTime from, LocalDateTime to, Long userId) {
        List<Entry> entries = entryRepository.findAllByDayBetweenAndUserId(from, to, userId);
        for (Entry e :  entries){
            e.setStatus(Status.SAVED);
            entryRepository.save(e);
        }
    }
}
