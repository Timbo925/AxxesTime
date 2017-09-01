package com.axxes.timesheet.time.service.impl;

import com.axxes.timesheet.time.domain.Entry;
import com.axxes.timesheet.time.domain.Percentage;
import com.axxes.timesheet.time.domain.Status;
import com.axxes.timesheet.time.domain.User;
import com.axxes.timesheet.time.exception.EntryException;
import com.axxes.timesheet.time.repository.EntryRepository;
import com.axxes.timesheet.time.repository.UserRepository;
import com.axxes.timesheet.time.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public void createEntryForUser(LocalDateTime from, LocalDateTime to, Long userId, Percentage percentage) {
        User user = userRepository.findOne(userId);
        Entry entry = new Entry();

        entry.setFrom(from);
        entry.setTo(to);

        entry.setUser(user);
        entry.setProject(user.getCurrentProject());
        entry.setPercentage(percentage);

        entry.setStatus(Status.OPEN);

        entryRepository.save(entry);
    }

    @Override
    public void savePeriodAsUser(LocalDateTime from, LocalDateTime to, Long userId) {
        List<Entry> entries = entryRepository.findAllByFromBetweenAndUserId(from, to, userId);
        for (Entry e :  entries){
            e.setStatus(Status.SAVED);
            entryRepository.save(e);
        }
    }

    @Override
    public void savePeriodAsAdmin(LocalDateTime from, LocalDateTime to, Long userId) {
        List<Entry>entries = entryRepository.findAllByFromBetweenAndUserId(from, to, userId);
        for (Entry e :  entries){
            e.setStatus(Status.LOCKED);
            entryRepository.save(e);
        }
    }

    @Override
    public void editPeriodAsAdmin(LocalDateTime from, LocalDateTime to, Long userId) {
        List<Entry>entries = entryRepository.findAllByFromBetweenAndUserId(from, to, userId);
        for (Entry e :  entries){
            e.setStatus(Status.SAVED);
            entryRepository.save(e);
        }
    }

    @Override
    public void editEntry(Long entryId, Entry entry) throws EntryException {
        if (Objects.equals(entry.getId(), entryId)){
            entryRepository.save(entry);
        } else {
            throw new EntryException("ID of old and new entry did not match.");
        }
    }

    @Override
    public Entry getEntry(Long entryId) {
        return entryRepository.findOne(entryId);
    }

}
