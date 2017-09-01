package com.axxes.timesheet.time.service;

import com.axxes.timesheet.time.domain.Entry;

import java.time.LocalDateTime;
import java.util.List;

public interface EntryService {

    Entry create(Entry entry, Long projectId);

    void saveEntriesForProject(LocalDateTime from, LocalDateTime to, Long projectId);

    Entry update(Entry entry);

    Entry getEntry(Long entryId);

    List<Entry> getEntriesBetweenFor(LocalDateTime startDate, LocalDateTime endDate, Long projectId);

    boolean lockEntries(LocalDateTime from, LocalDateTime to, Long projectId);

    void delete(Long entryId);
}
