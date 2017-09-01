package com.axxes.timesheet.time.service.impl;

import com.axxes.timesheet.time.domain.Contract;
import com.axxes.timesheet.time.domain.Entry;
import com.axxes.timesheet.time.domain.Percentage;
import com.axxes.timesheet.time.domain.Status;
import com.axxes.timesheet.time.exception.EntryException;
import com.axxes.timesheet.time.repository.EntryRepository;
import com.axxes.timesheet.time.repository.ProjectRepository;
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
    private ProjectRepository projectRepository;


    @Override
    public Entry createEntryForProject(LocalDateTime from, LocalDateTime to, Long projectId, Percentage percentage) {
        Contract contract = projectRepository.findOne(projectId);
        Entry entry = new Entry();
        entry.setFrom(from);
        entry.setTo(to);

        entry.setContract(contract);
        entry.setPercentage(percentage);

        entry.setStatus(Status.OPEN);

        return entryRepository.save(entry);
    }


    @Override
    public void saveEntriesForProject(LocalDateTime from, LocalDateTime to, Long projectId) {
        List<Entry> entries = entryRepository.findAllByFromBetweenAndContractId(from, to, projectId);
        for (Entry e : entries) {
            e.setStatus(Status.SAVED);
            entryRepository.save(e);
        }
    }


    @Override
    public void editPeriodAsAdmin(LocalDateTime from, LocalDateTime to, Long userId) {
        List<Entry> entries = entryRepository.findAllByFromBetweenAndContractId(from, to, userId);
        for (Entry e : entries) {
            e.setStatus(Status.SAVED);
            entryRepository.save(e);
        }
    }

    @Override
    public void editEntry(Long entryId, Entry entry) throws EntryException {
        if (Objects.equals(entry.getId(), entryId)) {
            entryRepository.save(entry);
        } else {
            throw new EntryException("ID of old and new entry did not match.");
        }
    }

    @Override
    public Entry getEntry(Long entryId) {
        return entryRepository.findOne(entryId);
    }

    @Override
    public List<Entry> getEntriesBetweenFor(LocalDateTime startDate, LocalDateTime endDate, Long projectId) {
        return null;
    }

    @Override
    public boolean lockEntries(LocalDateTime from, LocalDateTime to, Long projectId) {
        return false;
    }

}
