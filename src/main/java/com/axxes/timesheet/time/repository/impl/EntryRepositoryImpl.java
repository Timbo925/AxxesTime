package com.axxes.timesheet.time.repository.impl;

import com.axxes.timesheet.time.domain.Entry;
import com.axxes.timesheet.time.repository.ContractRepository;
import com.axxes.timesheet.time.repository.EntryRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class EntryRepositoryImpl implements EntryRepositoryCustom {

    @Autowired
    private ContractRepository contractRepository;

    @Override
    public List<Entry> findByContractIdAndBeginBetween(LocalDateTime begin, LocalDateTime end, Long contractId) {
        return contractRepository.findOne(contractId).getEntries();
    }
}
