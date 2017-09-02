package com.axxes.timesheet.time.facade.impl;

import com.axxes.timesheet.time.domain.Entry;
import com.axxes.timesheet.time.domain.Percentage;
import com.axxes.timesheet.time.domain.Status;
import com.axxes.timesheet.time.facade.EntryFacade;
import com.axxes.timesheet.time.facade.input.EntryInput;
import com.axxes.timesheet.time.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class EntryFacadeImpl implements EntryFacade {

    @Autowired
    private EntryService entryService;


    @Override
    public void createEntry(EntryInput input, Long projectId) {
        Entry entry = new Entry();

        entry.setBegin(LocalDateTime.ofInstant(Instant.ofEpochMilli(input.getBegin()), ZoneId.systemDefault()));
        entry.setTo(LocalDateTime.ofInstant(Instant.ofEpochMilli(input.getEnd()), ZoneId.systemDefault()));

        entry.setStatus(Status.valueOf(input.getStatus()));
        entry.setPercentage(Percentage.valueOf(input.getPercentage()));

        entryService.create(entry, projectId);
    }
}
