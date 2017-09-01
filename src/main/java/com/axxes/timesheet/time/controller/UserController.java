package com.axxes.timesheet.time.controller;

import com.axxes.timesheet.time.domain.User;
import com.axxes.timesheet.time.facade.UserFacade;
import com.axxes.timesheet.time.facade.input.UserInput;
import com.axxes.timesheet.time.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserFacade userFacade;

    @RequestMapping("/login")
    public void login() {

    }

    @RequestMapping("/logout")
    public void logout() {

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") Long userId) {
        return userService.get(userId);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void createUser(@RequestBody UserInput user) {
        userFacade.addUser(user);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteUser(Long userId) {
        userService.delete(userId);

    }

    @RequestMapping(value = "/update", method = RequestMethod.PATCH)
    public void updateUser(@RequestBody User user) {
        userService.update(user);
    }

}
