package com.axxes.timesheet.time.controller;

import com.axxes.timesheet.time.domain.User;
import com.axxes.timesheet.time.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello/secure")
    public String helloSecure() {
        return "Hello Secure";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @RequestMapping("/login")
    public void login() {

    }

    @RequestMapping("/logout")
    public void logout() {

    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUser(Long userId) {
        return userService.get(userId);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void createUser() {

    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public void deleteUser(Long userId) {
        userService.delete(userId);

    }

    @RequestMapping(value = "/user", method = RequestMethod.PATCH)
    public void updateUser() {

    }


}
