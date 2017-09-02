package com.axxes.timesheet.time.facade.impl;

import com.axxes.timesheet.time.domain.User;
import com.axxes.timesheet.time.domain.UserType;
import com.axxes.timesheet.time.facade.UserFacade;
import com.axxes.timesheet.time.facade.dto.UserDto;
import com.axxes.timesheet.time.facade.input.UserInput;
import com.axxes.timesheet.time.facade.mapper.UserMapper;
import com.axxes.timesheet.time.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

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

    @Override
    public void addUser(UserInput userInput) {
        User user = new User();

        user.setFirstName(userInput.getFirstName());
        user.setLastName(userInput.getLastName());
        user.setEmail(userInput.getEmail());
        user.setPhone(userInput.getPhone());
        user.setPassword(userInput.getPassword());
        user.setVacation(0d);
        user.setRecup(0d);
        user.setContracts(new ArrayList<>());
        user.setUserType(UserType.valueOf(userInput.getUserType()));
        userService.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userService.update(user);
    }


}
