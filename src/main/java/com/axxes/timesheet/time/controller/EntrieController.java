package com.axxes.timesheet.time.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
public class EntrieController {

    @RequestMapping(value = "/entries", method = RequestMethod.GET)
    public void getEntries(@RequestParam(value="startDate") ZonedDateTime startDate, @RequestParam(value="endDate") ZonedDateTime endDate) {

    }


}
