package com.axxes.timesheet.time.service;

import com.axxes.timesheet.time.domain.Percentage;

import java.time.LocalDateTime;
import java.time.LocalTime;

public interface EntryService {

    void createEntryForUser(LocalTime from, LocalTime to, LocalDateTime day, Long userId, Percentage percentage);
    void savePeriodAsUser(LocalDateTime from, LocalDateTime to, Long userId);

}
