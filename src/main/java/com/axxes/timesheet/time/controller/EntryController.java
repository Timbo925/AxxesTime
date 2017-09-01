package com.axxes.timesheet.time.controller;

import com.axxes.timesheet.time.domain.Entry;
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

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Entry> getEntries(@RequestParam(value = "startDate") LocalDateTime startDate,
                                  @RequestParam(value = "endDate") LocalDateTime endDate,
                                  @RequestParam(value = "projectId") Long projectId) {

        return entryService.getEntriesBetweenFor(startDate, endDate, projectId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
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
    public Entry create(@RequestBody Entry entry, @PathVariable("id") Long projectId) {
        return entryService.create(entry, projectId);
    }
}
