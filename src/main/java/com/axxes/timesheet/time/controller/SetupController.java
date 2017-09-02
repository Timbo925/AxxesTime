package com.axxes.timesheet.time.controller;

import com.axxes.timesheet.time.domain.*;
import com.axxes.timesheet.time.service.ContractService;
import com.axxes.timesheet.time.service.EntryService;
import com.axxes.timesheet.time.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("/setup")
public class SetupController {

    @Autowired
    private EntryService entryService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
        public void setupAlltheUsers(){
        User user = new User();
        user.setRecup(0D);
        user.setFirstName("Rob");
        user.setLastName("Kenis");
        user.setEmail("rob.kenis@axxes.com");
        user.setPhone("1207");
        user.setUserType(UserType.CONSULTANT);
        user.setVacation(0D);
        user.setPassword("hahafuckoff");
        user.setContracts(new ArrayList<>());
        userService.addUser(user);
        setupAlltheContracts();
        setupAlltheEntries();
    }

    @RequestMapping(value = "/contract", method = RequestMethod.POST)
    public void setupAlltheContracts(){
        Contract contract = new Contract();
        contract.setEntries(new ArrayList<>());
        contract.setName("HR Opleiding");
        contract.setClient("Axxes");
        contract.setType(Type.TRAINING);
        contract.setAmountOfHours(8);
        userService.assignProjectToUser(1L, contract);
    }

    @RequestMapping(value = "/entry", method = RequestMethod.POST)
    public void setupAlltheEntries(){
        Entry entry = new Entry();
        entry.setBegin(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
        entry.setTo(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
        entry.setPercentage(Percentage.TWO_HUNDRED);
        entry.setStatus(Status.OPEN);
        entryService.create(entry,1L);
        Entry entry1 = new Entry();
        entry1.setBegin(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
        entry1.setTo(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
        entry1.setPercentage(Percentage.TWO_HUNDRED);
        entry1.setStatus(Status.OPEN);
        entryService.create(entry1, 1L);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Entry> retrieveAlltheEntries(){
        return asList(entryService.getEntry(1L));
    }
}
