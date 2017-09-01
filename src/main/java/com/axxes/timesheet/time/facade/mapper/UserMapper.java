package com.axxes.timesheet.time.facade.mapper;

import com.axxes.timesheet.time.domain.User;
import com.axxes.timesheet.time.facade.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto mapToUser(User user){
        UserDto dto = new UserDto();

        return dto;
    }
}
