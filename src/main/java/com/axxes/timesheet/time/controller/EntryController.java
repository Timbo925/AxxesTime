package com.axxes.timesheet.time.controller;

import com.axxes.timesheet.time.domain.Entry;
import com.axxes.timesheet.time.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController(value = "/entry")
public class EntryController {

    @Autowired
    private EntryService entryService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Entry> getEntries(@RequestParam(value = "startDate") LocalDateTime startDate,
                                  @RequestParam(value = "endDate") LocalDateTime endDate,
                                  @RequestParam(value = "projectId") Long projectId) {

        return entryService.getEntriesBetweenFor(startDate, endDate, projectId);
    }


}
