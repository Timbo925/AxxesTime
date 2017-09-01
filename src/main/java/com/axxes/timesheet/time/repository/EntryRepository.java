package com.axxes.timesheet.time.repository;

import com.axxes.timesheet.time.domain.Entry;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EntryRepository extends CrudRepository<Entry, Long> {

    List<Entry> findAllByFromBetweenAndUserIdAndProjectId(LocalDateTime after, LocalDateTime before, Long userId, Long projectId);
    List<Entry> findAllByFromBetweenAndUserId(LocalDateTime after, LocalDateTime before, Long userId);
    List<Entry> findAllByFromBetween(LocalDate after, LocalDate before);
}
