package com.axxes.timesheet.time.service.impl;

import com.axxes.timesheet.time.domain.Entry;
import com.axxes.timesheet.time.domain.Percentage;
import com.axxes.timesheet.time.domain.Project;
import com.axxes.timesheet.time.domain.Status;
import com.axxes.timesheet.time.repository.EntryRepository;
import com.axxes.timesheet.time.repository.ProjectRepository;
import com.axxes.timesheet.time.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private ProjectRepository projectRepository;


    @Override
    public Entry createEntryForProject(LocalDateTime from, LocalDateTime to, Long projectId, Percentage percentage) {
        Project project = projectRepository.findOne(projectId);
        Entry entry = new Entry();
        entry.setFrom(from);
        entry.setTo(to);

        entry.setProject(project);
        entry.setPercentage(percentage);

        entry.setStatus(Status.OPEN);

        return entryRepository.save(entry);
    }

    @Override
    public void saveEntriesForProject(LocalDateTime from, LocalDateTime to, Long projectId) {
        List<Entry> entries = entryRepository.findAllByDayBetweenAndProjectId(from, to, projectId);
        for (Entry e : entries) {
            e.setStatus(Status.SAVED);
            entryRepository.save(e);
        }
    }

    @Override
    public List<Entry> getEntriesBetweenFor(LocalDateTime startDate, LocalDateTime endDate, Long projectId) {
        return entryRepository.getEntriesBetweenFor(startDate, endDate, projectId);
    }

    @Override
    public boolean lockEntries(LocalDateTime from, LocalDateTime to, Long projectId) {
        List<Entry> entriesBetweenFor = getEntriesBetweenFor(from, to, projectId);

        return false;
    }
}
