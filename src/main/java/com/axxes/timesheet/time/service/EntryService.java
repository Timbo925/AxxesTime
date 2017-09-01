package com.axxes.timesheet.time.service;

import com.axxes.timesheet.time.domain.Entry;
import com.axxes.timesheet.time.domain.Percentage;

import java.time.LocalDateTime;
import java.util.List;

public interface EntryService {

    void createEntryForProject(LocalDateTime from, LocalDateTime to, Long projectId, Percentage percentage);
    void savePeriodInProject(LocalDateTime from, LocalDateTime to, Long projectId);
    void savePeriodAsAdmin(LocalDateTime from, LocalDateTime to, Long projectId);
    void editPeriodAsAdmin(LocalDateTime from, LocalDateTime to, Long projectId);
    void editEntry(Long entryId, Entry entry) throws EntryException;
    Entry getEntry(Long entryId);

    void saveEntriesForProject(LocalDateTime from, LocalDateTime to, Long projectId);

    List<Entry> getEntriesBetweenFor(LocalDateTime startDate, LocalDateTime endDate, Long projectId);


    boolean lockEntries(LocalDateTime from, LocalDateTime to, Long projectId);
}
