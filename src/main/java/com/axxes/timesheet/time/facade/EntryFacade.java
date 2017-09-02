package com.axxes.timesheet.time.facade;

import com.axxes.timesheet.time.facade.input.EntryInput;

public interface EntryFacade {

    void createEntry(EntryInput input, Long projectId);
}
