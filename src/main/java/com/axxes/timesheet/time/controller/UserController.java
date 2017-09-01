package com.axxes.timesheet.time.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

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

    @RequestMapping(value="/user", method = RequestMethod.GET)
    public void user() {

    }


}
