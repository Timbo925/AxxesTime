package com.axxes.timesheet.time.facade;

import com.axxes.timesheet.time.domain.User;
import com.axxes.timesheet.time.facade.dto.UserDto;
import com.axxes.timesheet.time.facade.input.UserInput;

public interface UserFacade {

    UserDto getUser(Long id);
    void addUser(UserInput userInput);
    void updateUser(User user);
}
