package com.axxes.timesheet.time.service.impl;

import com.axxes.timesheet.time.domain.Contract;
import com.axxes.timesheet.time.domain.Entry;
import com.axxes.timesheet.time.domain.Status;
import com.axxes.timesheet.time.repository.ContractRepository;
import com.axxes.timesheet.time.repository.EntryRepository;
import com.axxes.timesheet.time.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private ContractRepository contractRepository;


    @Override
    public Entry create(Entry entry, Long projectId) {
        Contract contract = contractRepository.findOne(projectId);
        Entry savedEntry = entryRepository.save(entry);
        contract.getEntries().add(savedEntry);
        contractRepository.save(contract);
        return savedEntry;
    }

    @Override
    public void saveEntriesForProject(LocalDateTime from, LocalDateTime to, Long projectId) {
        List<Entry> entries = entryRepository.findAllByBeginBetweenAndContractId(from, to, projectId);
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
    public List<Entry> getEntriesBetweenFor(LocalDateTime startDate, LocalDateTime endDate, Long contractId) {
        Contract contract = contractRepository.findOne(contractId);
        List<Entry> entries = contract.getEntries();
        return entries.stream().filter(entry -> !entry.getBegin().isBefore(startDate) && (!entry.getTo().isAfter(endDate))).collect(Collectors.toList());

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
