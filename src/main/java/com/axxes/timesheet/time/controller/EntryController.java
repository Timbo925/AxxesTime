package com.axxes.timesheet.time.controller;

import com.axxes.timesheet.time.domain.Entry;
import com.axxes.timesheet.time.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class EntryController {

    @Autowired
    private EntryService entryService;

    @RequestMapping(value = "/entries", method = RequestMethod.GET)
    public List<Entry> getEntries(@RequestParam(value = "startDate") LocalDate startDate,
                                  @RequestParam(value = "endDate") LocalDate endDate,
                                  @RequestParam(value = "projectId") Long projectId) {

        return entryService.getEntriesBetweenFor(startDate.atStartOfDay(), endDate.atStartOfDay(), projectId);
    }


}
