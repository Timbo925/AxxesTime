package com.axxes.timesheet.time.controller;

import com.axxes.timesheet.time.domain.Contract;
import com.axxes.timesheet.time.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/projects")
public class ContractController {


    @Autowired
    private ContractService contractService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Contract> getContracts(Long userId) {
        return contractService.getAllForUser(userId);
    }


}
