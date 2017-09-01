package com.axxes.timesheet.time.service;

import com.axxes.timesheet.time.domain.Entry;
import com.axxes.timesheet.time.domain.Percentage;
import com.axxes.timesheet.time.exception.EntryException;

import java.time.LocalDateTime;
import java.time.LocalTime;

public interface EntryService {

    void createEntryForUser(LocalDateTime from, LocalDateTime to, Long userId, Percentage percentage);
    void savePeriodAsUser(LocalDateTime from, LocalDateTime to, Long userId);
    void savePeriodAsAdmin(LocalDateTime from, LocalDateTime to, Long userId);
    void editPeriodAsAdmin(LocalDateTime from, LocalDateTime to, Long userId);
    void editEntry(Long entryId, Entry entry) throws EntryException;
    Entry getEntry(Long entryId);

}
