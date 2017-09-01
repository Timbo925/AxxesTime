package com.axxes.timesheet.time.repository;

import com.axxes.timesheet.time.domain.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {

}
