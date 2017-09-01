package com.axxes.timesheet.time.repository;

import com.axxes.timesheet.time.domain.Entry;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EntryRepository extends CrudRepository<Entry, Long> {

    List<Entry> findAllByBeginBetweenAndContractId(LocalDateTime before, LocalDateTime after, Long contractId);
}
