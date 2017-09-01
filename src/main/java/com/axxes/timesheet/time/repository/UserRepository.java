package com.axxes.timesheet.time.repository;

import com.axxes.timesheet.time.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends CrudRepository<User, Long> {

}
