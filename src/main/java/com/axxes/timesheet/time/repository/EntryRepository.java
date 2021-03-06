package com.axxes.timesheet.time.repository;

import com.axxes.timesheet.time.domain.Entry;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EntryRepository extends CrudRepository<Entry, Long>, EntryRepositoryCustom {

    List<Entry> findAllByBeginBetween(LocalDateTime begin, LocalDateTime localDateTime);
}
