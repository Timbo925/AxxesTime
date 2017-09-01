package com.axxes.timesheet.time.facade.impl;

import com.axxes.timesheet.time.facade.UserFacade;
import com.axxes.timesheet.time.facade.dto.UserDto;
import com.axxes.timesheet.time.facade.mapper.UserMapper;
import com.axxes.timesheet.time.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Override
    public UserDto getUser(Long id) {
        return userMapper.mapToUser(userService.get(id));
    }
}
