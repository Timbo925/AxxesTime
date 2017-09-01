package com.axxes.timesheet.time.repository;

import com.axxes.timesheet.time.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmailAnd(String email);

}
