package com.axxes.timesheet.time.repository;

import com.axxes.timesheet.time.domain.Contract;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Contract, Long> {

}
