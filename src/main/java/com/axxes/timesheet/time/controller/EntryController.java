package com.axxes.timesheet.time.controller;

import com.axxes.timesheet.time.domain.Entry;
import com.axxes.timesheet.time.facade.EntryFacade;
import com.axxes.timesheet.time.facade.input.EntryInput;
import com.axxes.timesheet.time.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/entry")
public class EntryController {

    @Autowired
    private EntryService entryService;

    @Autowired
    private EntryFacade entryFacade;

    @RequestMapping(value = "/period/all", method = RequestMethod.GET)
    public List<Entry> getEntries(@RequestParam(value = "startDate") LocalDateTime startDate,
                                  @RequestParam(value = "endDate") LocalDateTime endDate,
                                  @RequestParam(value = "projectId") Long projectId) {

        return entryService.getEntriesBetweenFor(startDate, endDate, projectId);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Entry> getEntriesPeriod(@RequestParam(value = "startDate") LocalDateTime startDate,
                                        @RequestParam(value = "period") String period,
                                        @RequestParam(value = "userId") Long userId) {

        return entryService.getEntriesInPeriodForUser(startDate, period, userId);
    }


    @GetMapping(value = "/{id}")
    public Entry getEntry(@PathVariable("id") Long entryId) {
        return entryService.getEntry(entryId);
    }

    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable("id") Long entryId) {
        entryService.delete(entryId);
    }

    @PutMapping()
    public Entry update(@RequestBody Entry entry) {
        return entryService.update(entry);
    }

    @PostMapping("/{id}")
    public void create(@RequestBody EntryInput entry, @PathVariable("id") Long projectId) {
        entryFacade.createEntry(entry, projectId);
    }
}
