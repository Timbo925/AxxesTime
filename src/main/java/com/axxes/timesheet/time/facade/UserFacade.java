package com.axxes.timesheet.time.facade;

import com.axxes.timesheet.time.facade.dto.UserDto;

public interface UserFacade {

    UserDto getUser(Long id);
}
