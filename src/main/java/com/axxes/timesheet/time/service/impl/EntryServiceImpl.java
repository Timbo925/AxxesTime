package com.axxes.timesheet.time.service.impl;

import com.axxes.timesheet.time.domain.Contract;
import com.axxes.timesheet.time.domain.Entry;
import com.axxes.timesheet.time.domain.Status;
import com.axxes.timesheet.time.repository.ContractRepository;
import com.axxes.timesheet.time.repository.EntryRepository;
import com.axxes.timesheet.time.repository.UserRepository;
import com.axxes.timesheet.time.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private UserRepository userRepository;


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
        List<Entry> entries = entryRepository.findByContractIdAndBeginBetween(from, to, projectId);
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

    @Override
    public List<Entry> getEntriesInPeriodForUser(LocalDateTime startDate, String period, Long userId) {
        List<Contract>contracts = userRepository.findOne(userId).getContracts();
        List<Entry>entries = new ArrayList<>();
        for (Contract c : contracts){
           entries.addAll(entryRepository.findByContractIdAndBeginBetween(findStartDateForPeriod(startDate, period), findEndDateForPeriod(startDate, period), c.getId()));
        }
        return entries;
    }

    private LocalDateTime findEndDateForPeriod(LocalDateTime startDate, String period){
        switch (period){
            case "WEEK":
                return findStartDateForPeriod(startDate, period).plusDays(7);
            case "MONTH":
                return findStartDateForPeriod(startDate, period).plusDays(30);
            case "YEAR":
                return findStartDateForPeriod(startDate, period).plusDays(365);
        }
        return startDate;
    }

    private LocalDateTime findStartDateForPeriod(LocalDateTime startDate, String period){
        switch (period){
            case "WEEK":
                return startDate.minusDays(startDate.getDayOfWeek().getValue());
            case "MONTH":
                return startDate.minusDays(startDate.getDayOfMonth());
            case "YEAR":
                return startDate.minusDays(startDate.getDayOfMonth());
        }
        return startDate;
    }

}
