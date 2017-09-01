package com.axxes.timesheet.time.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
public class EntryController {

    @RequestMapping(value = "user/entries", method = RequestMethod.GET)
    public void getEntries(@RequestParam(value="startDate") ZonedDateTime startDate, @RequestParam(value="endDate") ZonedDateTime endDate) {

    }


}
