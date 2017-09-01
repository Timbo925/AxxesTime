package com.axxes.timesheet.time.service.impl;

import com.axxes.timesheet.time.domain.Contract;
import com.axxes.timesheet.time.domain.Entry;
import com.axxes.timesheet.time.domain.Percentage;
import com.axxes.timesheet.time.domain.Status;
import com.axxes.timesheet.time.repository.ContractRepository;
import com.axxes.timesheet.time.repository.EntryRepository;
import com.axxes.timesheet.time.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private ContractRepository contractRepository;


    @Override
    public Entry createEntryForProject(LocalDateTime from, LocalDateTime to, Long projectId, Percentage percentage) {
        Contract contract = contractRepository.findOne(projectId);
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
    public Entry update(Entry entry) {
        return entryRepository.save(entry);
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

    @Override
    public void delete(Long entryId) {
        entryRepository.delete(entryRepository.findOne(entryId));
    }

}
