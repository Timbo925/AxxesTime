package com.axxes.timesheet.time.repository;

import com.axxes.timesheet.time.domain.Entry;

import java.time.LocalDateTime;
import java.util.List;

public interface EntryRepositoryCustom {

    List<Entry> findByContractIdAndBeginBetween(LocalDateTime begin, LocalDateTime end, Long contractId);
}
