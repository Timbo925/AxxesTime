package com.axxes.timesheet.time.repository;

import com.axxes.timesheet.time.domain.Contract;
import com.axxes.timesheet.time.domain.Entry;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContractRepository extends CrudRepository<Contract, Long> {

}
